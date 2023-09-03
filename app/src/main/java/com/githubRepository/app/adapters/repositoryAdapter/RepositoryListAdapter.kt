package com.githubRepository.app.adapters.repositoryAdapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.githubRepository.utils.interfaces.OnRepositoryItemClickListener
import com.githubRepository.databinding.LayoutRepositoryBinding
import com.githubRepository.domain.model.githubList.GithubRepo
import com.githubRepository.utils.extensions.loadImageGlide

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

    fun append(items: ArrayList<GithubRepo>) {
        mList.addAll(items)
        notifyItemRangeInserted(itemCount, items.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(item: GithubRepo) {
        mList.removeIf { it == item }
        notifyDataSetChanged()
    }

    fun addItem(item: GithubRepo) {
        mList.add(item)
        notifyItemChanged(itemCount - 1)
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
