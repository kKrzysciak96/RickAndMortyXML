package com.example.rickandmortyxml.core.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStateProviderImpl(private val connectivityManager: ConnectivityManager) :
    NetworkStateProvider {


    override fun isNetworkAvailable(): Boolean {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}