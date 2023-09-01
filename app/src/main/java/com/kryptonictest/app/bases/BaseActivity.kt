package com.kryptonictest.app.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kryptonictest.utils.general.GeneralMethods

abstract class BaseActivity : AppCompatActivity() {
    fun showTopError(strMessage: String) {
        GeneralMethods.showTopError(strMessage, this)
    }

    fun showTopSuccess(strMessage: String) {
        GeneralMethods.showTopSuccess(strMessage, this)
    }
}