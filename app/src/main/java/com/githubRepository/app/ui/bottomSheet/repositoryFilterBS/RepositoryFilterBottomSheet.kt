package com.githubRepository.app.ui.bottomSheet.repositoryFilterBS

import android.os.Bundle
import android.view.View
import com.githubRepository.R
import com.githubRepository.app.bases.BaseBottomSheet
import com.githubRepository.databinding.BottomSheetRepositoryFilterBinding
import com.githubRepository.domain.model.general.RepositoryFilterType
import com.githubRepository.utils.interfaces.OnSelectRepositoryFilterListener
import com.githubRepository.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryFilterBottomSheet : BaseBottomSheet(R.layout.bottom_sheet_repository_filter) {
    private val binding by viewBinding(BottomSheetRepositoryFilterBinding::bind)

    private var onSelectRepositoryFilterListener: OnSelectRepositoryFilterListener? = null

    companion object {
        fun showRepositoryFilterBottomSheet() = RepositoryFilterBottomSheet()
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        iniItems()
    }

    private fun iniItems() {
        binding.btnClose.setOnClickListener { dismiss() }

        binding.tvLastDay.setOnClickListener {
            onSelectRepositoryFilterListener?.onSelectFilter(RepositoryFilterType.LastDayFilter)
            dismiss()
        }

        binding.tvLastWeek.setOnClickListener {
            onSelectRepositoryFilterListener?.onSelectFilter(RepositoryFilterType.LastWeekFilter)
            dismiss()
        }

        binding.tvLastMonth.setOnClickListener {
            onSelectRepositoryFilterListener?.onSelectFilter(RepositoryFilterType.LastMonthFilter)
            dismiss()
        }
    }

    fun setOnItemClickListener(onSelectRepositoryFilterListener: OnSelectRepositoryFilterListener?) {
        this.onSelectRepositoryFilterListener = onSelectRepositoryFilterListener
    }
}