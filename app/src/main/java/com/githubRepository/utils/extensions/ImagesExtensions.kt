package com.githubRepository.utils.extensions

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.githubRepository.R
import com.mikhaellopez.circularimageview.CircularImageView


fun CircularImageView.loadImageGlide(url: String?) {
    url.let {
        Glide.with(context).load(url).skipMemoryCache(true).onlyRetrieveFromCache(true)
            .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_no_img).into(this)
    }
}