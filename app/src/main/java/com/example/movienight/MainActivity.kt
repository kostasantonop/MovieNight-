package com.example.movienight

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
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

    private fun observeNetworkStatus() {
        lifecycleScope.launch {
            connectivityObserver.observe().collect { status ->
                when (status) {
                    ConnectivityObserver.Status.Available -> {
                        Alerter.hide()
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
                .enableInfiniteDuration(true)
                .setOnClickListener {
                    startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
                }
                .show()
        }
    }
}



