package com.kryptonictest.app.adapters.loadMoreAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kryptonictest.databinding.LayoutLoadingMoreBinding


class LoadingMoreAdapter :
    RecyclerView.Adapter<LoadingMoreAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutLoadingMoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

    }

    inner class MainViewHolder(binding: LayoutLoadingMoreBinding) :
        RecyclerView.ViewHolder(binding.root)
}
