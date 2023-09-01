package com.kryptonictest.app.ui.bottomSheet.repositoryDetailsBS

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kryptonictest.R
import com.kryptonictest.app.bases.BaseBottomSheet
import com.kryptonictest.databinding.BottomSheetRepositoryDetailsBinding
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.utils.extensions.newParcelable
import com.kryptonictest.utils.general.GeneralMethods.openUrlFromExternal
import com.kryptonictest.utils.interfaces.OnChangeRepositoryFavoriteListener
import com.kryptonictest.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryDetailsBottomSheet : BaseBottomSheet(R.layout.bottom_sheet_repository_details) {
    private val binding by viewBinding(BottomSheetRepositoryDetailsBinding::bind)

    private val viewModel: RepositoryDetailsViewModel by viewModels()
    private var onChangeFavoriteListener: OnChangeRepositoryFavoriteListener? = null

    companion object {
        fun showRepositoryDetailsBottomSheet(githubRepo: GithubRepo) =
            RepositoryDetailsBottomSheet().apply {
                arguments = Bundle().apply {
                    putParcelable("itemObject", githubRepo)
                }
            }
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.fillData(arguments?.newParcelable("itemObject"))

        iniItems()
        handleObservers()

    }

    private fun iniItems() {
        binding.btnClose.setOnClickListener { dismiss() }
    }

    private fun handleObservers() {
        viewModel.itemFavoriteChanged.observe(viewLifecycleOwner) { isAddToFavorite ->
            onChangeFavoriteListener?.onChange(viewModel.githubRepoItem!!, !isAddToFavorite)
        }

        viewModel.getOpenRepoOnGitHub().observe(viewLifecycleOwner) {
            openUrlFromExternal(it.html_url, requireActivity())
        }
    }

    fun setOnItemClickListener(onChangeFavoriteListener: OnChangeRepositoryFavoriteListener?) {
        this.onChangeFavoriteListener = onChangeFavoriteListener
    }
}