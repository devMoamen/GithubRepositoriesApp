package com.kryptonictest.app.ui.bottomSheet.repositoryFilterBS

import android.os.Bundle
import android.view.View
import com.kryptonictest.R
import com.kryptonictest.app.bases.BaseBottomSheet
import com.kryptonictest.databinding.BottomSheetRepositoryDetailsBinding
import com.kryptonictest.databinding.BottomSheetRepositoryFilterBinding
import com.kryptonictest.domain.model.general.RepositoryFilterType
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.utils.extensions.newParcelable
import com.kryptonictest.utils.general.GeneralMethods.getLastDayData
import com.kryptonictest.utils.general.GeneralMethods.getLastMonthData
import com.kryptonictest.utils.general.GeneralMethods.getLastWeekData
import com.kryptonictest.utils.general.GeneralMethods.openUrlFromExternal
import com.kryptonictest.utils.interfaces.OnChangeRepositoryFavoriteListener
import com.kryptonictest.utils.interfaces.OnSelectRepositoryFilterListener
import com.kryptonictest.utils.viewBinding.viewBinding
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