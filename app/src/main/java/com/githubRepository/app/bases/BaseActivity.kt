package com.githubRepository.app.bases

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.githubRepository.R
import com.githubRepository.utils.general.GeneralMethods
import com.githubRepository.utils.general.NetworkChangeReceiver

abstract class BaseActivity : AppCompatActivity() {
    private var networkChangeReceiverReceiver: NetworkChangeReceiver? = null

    fun showTopError(strMessage: String) {
        GeneralMethods.showTopError(strMessage, this)
    }

    fun showTopSuccess(strMessage: String) {
        GeneralMethods.showTopSuccess(strMessage, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBroadcastReceiver()
    }

    private fun registerBroadcastReceiver() {
        networkChangeReceiverReceiver = NetworkChangeReceiver()
        networkChangeReceiverReceiver?.init(
            object : NetworkChangeReceiver.LocationListener {
                override fun onChangeInternetStatus(hasInternetNet: Boolean) {
                    if (!hasInternetNet) {
                        showTopError(getString(R.string.check_internet_connection))
                    }
                }
            }
        )
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(networkChangeReceiverReceiver, filter)
    }


    override fun onDestroy() {
        super.onDestroy()
        if (networkChangeReceiverReceiver != null) unregisterReceiver(networkChangeReceiverReceiver)
    }
}