package com.kryptonictest.utils.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kryptonictest.R
import com.kryptonictest.app.adapters.repositoryAdapter.RepositoryListAdapter
import com.kryptonictest.utils.general.EndlessRecyclerOnScrollListener
import com.kryptonictest.utils.interfaces.OnLoadingMoreListener
import com.kryptonictest.utils.itemDecoration.MarginItemDecoration
import com.mikhaellopez.circularimageview.CircularImageView

object GeneralBindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageGlide")
    fun loadImageGlide(view: CircularImageView, url: String?) {
        url.let {
            Glide.with(view.context).load(url)   .skipMemoryCache(true).onlyRetrieveFromCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_no_img).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("isRefreshing")
    fun isSwipeRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean) {
        view.isRefreshing = isRefreshing
    }

    @JvmStatic
    @BindingAdapter("concatAdapter", "loadingMore", "isRefreshAdapter")
    fun concatAdapter(
        view: RecyclerView,
        concatAdapter: ConcatAdapter,
        loadingMore: OnLoadingMoreListener,isRefreshAdapter: Boolean
    ) {
        with(view) {
            adapter = concatAdapter
            addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
                override fun onLoadMore() {
                    loadingMore.onLoadingMore()
                }
            })
        }
    }

    @JvmStatic
    @BindingAdapter("marginDecoration")
    fun marginDecoration(view: RecyclerView, marginDecoration: Int) {
        view.addItemDecoration(MarginItemDecoration(marginDecoration))
    }

    @JvmStatic
    @BindingAdapter("favoritesAdapter")
    fun favoritesAdapter(view: RecyclerView, mAdapter: RepositoryListAdapter) {
        with(view) {
            adapter = mAdapter
        }
    }
}