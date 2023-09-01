package com.kryptonictest.app.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kryptonictest.R
import com.kryptonictest.app.bases.BaseFragment
import com.kryptonictest.app.ui.bottomSheet.repositoryDetails.RepositoryDetailsBottomSheet
import com.kryptonictest.databinding.FragmentHomeBinding
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        handleObservers()
    }

    private fun handleObservers() {
        viewModel.getOpenRepoDetails().observe(viewLifecycleOwner) {
            showDetailsBottomSheet(it);
        }

        viewModel.showErrorMessage.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                showTopError(it)
            }
        }

        viewModel.showSuccessMessage.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                showTopSuccess(it)
            }
        }
    }

    private fun showDetailsBottomSheet(item: GithubRepo) {
        RepositoryDetailsBottomSheet.showRepositoryDetailsBottomSheet(item)
            .show(childFragmentManager, javaClass.canonicalName)
    }
}