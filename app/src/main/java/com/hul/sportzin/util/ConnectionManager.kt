package com.hul.sportzin.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.lifecycle.LiveData

class ConnectionManager(private val context: Context) : LiveData<Boolean>() {

    private var mConnectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var mNetworkCallback: ConnectivityManager.NetworkCallback

    private fun connectivityManagerCallback(): ConnectivityManager.NetworkCallback {

        mNetworkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onLost(network: Network) {
                super.onLost(network)

                postValue(false)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                postValue(true)
            }
        }
        return mNetworkCallback
    }

    private val mNetworkReceiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }

    }

    private fun updateConnection() {
        val mActiveNetwork:NetworkInfo? = mConnectivityManager.activeNetworkInfo
        postValue(mActiveNetwork?.isConnected == true)
    }

    override fun onActive() {
        super.onActive()

        updateConnection()
        context.registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onInactive() {
        super.onInactive()

        context.unregisterReceiver(mNetworkReceiver)
    }
}