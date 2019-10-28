package ru.stowawaydev.template.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import timber.log.Timber

/**
 * template header (replace it)
 */

class ConnectionUtil(context: Context) {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun observeConnection(
        networkCallback: ConnectivityManager.NetworkCallback = object :
            ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network?) {
                Timber.d("Network has lost")
            }

            override fun onUnavailable() {
                Timber.d("Network is unavailable")
            }

            override fun onLosing(network: Network?, maxMsToLive: Int) {
                Timber.d("Network is losing")
            }

            override fun onAvailable(network: Network?) {
                Timber.d("Network is available")
            }
        }
    ) {
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun unregisterObserver(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
