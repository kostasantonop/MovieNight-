package com.example.movienight

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movienight.databinding.ActivityMainBinding
import com.example.movienight.viewpager.ViewPagerFragment
import com.tapadoo.alerter.Alerter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var connectivityManager: ConnectivityManager

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, ViewPagerFragment())
            .commit()

        //connectivity manager
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        networkCallback = object : ConnectivityManager.NetworkCallback() {
            //network lost case
            override fun onLost(network: Network) {
                super.onLost(network)
                notifyNetworkChange(false)
                this.let {
                    Alerter.create(this@MainActivity)
                        .setTitle("NetWork lost...")
                        .setText("It seems your connection is lost. Please check your connection or connect to another network.")
                        .setDuration(10000)
                        .setBackgroundColorRes(R.color.red)
                        .show()
                }
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun notifyNetworkChange(isConnected: Boolean) {
        // Notify all fragments or any component interested in network changes
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is NetworkChangeListener) {
                fragment.onNetworkChanged(isConnected)
            }
        }
    }

    interface NetworkChangeListener { //αυτο θα γινεται implement απο τα fragment ωστε να μπορουν να καλουν την onNetworkChanged
        fun onNetworkChanged(isConnected: Boolean) //αυτη θα καλειται απο το καθε fragment ωστε να γινεται ο ελεγχος συνδεσης
    }
}
