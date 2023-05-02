package com.studi.workshopmovieapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import timber.log.Timber

fun Context.isOnline(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Timber.i("Internet --> NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Timber.i("Internet --> NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Timber.i("Internet --> NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}