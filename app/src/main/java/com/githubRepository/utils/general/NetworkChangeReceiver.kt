package com.githubRepository.utils.general

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.githubRepository.utils.manager.NetworkStateManager


class NetworkChangeReceiver : BroadcastReceiver() {
    private var locationListener: LocationListener? = null
    fun init(locationListener: LocationListener) {
        this.locationListener = locationListener
    }
    override fun onReceive(context: Context, intent: Intent) {
        intent.action?.let { act ->
            if (act.matches("android.net.conn.CONNECTIVITY_CHANGE".toRegex())) {
                locationListener?.onChangeInternetStatus(NetworkStateManager.isInternetAvailable(context))
            }
        }
    }

    interface LocationListener {
        fun onChangeInternetStatus(hasInternetNet: Boolean)
    }
}
