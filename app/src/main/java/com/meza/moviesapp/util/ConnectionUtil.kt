package com.meza.moviesapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import java.lang.Exception

object ConnectionUtil {
    private var applicationContext: Context? = null

    fun init(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

    fun isNetworkAvailable(): Boolean {
        try {
            val connectivityManager =
                applicationContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } catch (e: Exception) {
            Log.e("NetworkUtils", "Exception happened: ${e.message}")
            return false
        }
    }
}