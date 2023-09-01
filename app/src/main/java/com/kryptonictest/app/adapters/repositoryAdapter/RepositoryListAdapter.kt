package com.kryptonictest.app.adapters.repositoryAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kryptonictest.utils.interfaces.OnRepositoryItemClickListener
import com.kryptonictest.databinding.LayoutRepositoryBinding
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.utils.extensions.loadImageGlide

class RepositoryListAdapter :
    RecyclerView.Adapter<RepositoryListAdapter.MainViewHolder>() {

    private val mList = ArrayList<GithubRepo>()

    private var onItemClickListener: OnRepositoryItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnRepositoryItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: ArrayList<GithubRepo>) {
        mList.clear()
        mList.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun append(items: ArrayList<GithubRepo>) {
        mList.addAll(items)
        notifyItemRangeInserted(itemCount, items.size)
    }

    fun changeItem(position: Int, item: GithubRepo) {
        if (mList.isNotEmpty()) {
            mList[position] = item
            notifyItemChanged(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAll() {
        mList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun isEmpty(): Boolean {
        return mList.isEmpty()
    }

    inner class MainViewHolder(private val binding: LayoutRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.openRepositoryDetails(mList[bindingAdapterPosition])
            }
        }

        fun bind(githubRepo: GithubRepo) {
            with(binding) {
                with(githubRepo) {
                    tvTitle.text = full_name
                    tvDescription.text = getDescriptionStr()
                    tvStars.text = stargazers_count.toString()
                    imgAvatar.loadImageGlide(owner?.avatar_url)
                    tvLastUpdate.text = getLastUpdateStr()
                }
            }
        }
    }
}
