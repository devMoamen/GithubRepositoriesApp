package com.kryptonictest.utils.extensions

import com.bumptech.glide.Glide
import com.kryptonictest.R
import com.mikhaellopez.circularimageview.CircularImageView


fun CircularImageView.loadImageGlide(url: String?) {
    url.let {
        Glide.with(context).load(url).placeholder(R.drawable.ic_no_img).into(this)
    }
}