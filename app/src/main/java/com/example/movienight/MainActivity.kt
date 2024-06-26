package com.example.movienight

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.movienight.viewpager.ViewPagerFragment
import com.example.movienight.viewpager.fragment.NoInternetFragment
import com.example.movienight.viewpager.network.ConnectivityObserver
import com.example.movienight.viewpager.network.NetworkConnectivityObserver
import com.tapadoo.alerter.Alerter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var connectivityObserver: NetworkConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        val initialStatus = connectivityObserver.checkInitialNetworkStatus()
        if (initialStatus == ConnectivityObserver.Status.Available) {
            loadFragment(ViewPagerFragment())
        } else {
            loadFragment(NoInternetFragment())
            showAlerter("Network Status", "No network connection.", R.color.dark_grey)
        }
        observeNetworkStatus()
    }

    private fun checkNetworkStatus(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    private fun observeNetworkStatus() {
        lifecycleScope.launch {
            connectivityObserver.observe().collect { status ->
                when (status) {
                    ConnectivityObserver.Status.Available -> {
                        loadFragment(ViewPagerFragment())
                    }
                    ConnectivityObserver.Status.Lost -> {
                        showAlerter("Network Status", "No network connection.", R.color.dark_grey)
                        loadFragment(NoInternetFragment())
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun showAlerter(title: String, text: String, colorRes: Int) {
        runOnUiThread {
            Alerter.hide()
            Alerter.create(this@MainActivity)
                .setTitle(title)
                .setText(text)
                .setBackgroundColorRes(colorRes)
                .show()
        }
    }
}



