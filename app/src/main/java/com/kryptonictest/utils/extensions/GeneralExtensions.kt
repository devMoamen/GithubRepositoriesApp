package com.kryptonictest.utils.extensions

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable


inline fun <reified T : Parcelable> Intent.newParcelable(key: String): T? {
    return if (SDK_INT >= 33) {
        getParcelableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }
}

inline fun <reified T> Bundle.newParcelable(key: String): T? {
    return if (SDK_INT >= 33) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION") getParcelable(key) as? T
    }
}