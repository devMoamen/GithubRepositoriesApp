package com.kryptonictest.app.ui.main.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kryptonictest.R
import com.kryptonictest.app.bases.BaseFragment
import com.kryptonictest.app.ui.bottomSheet.repositoryDetailsBS.RepositoryDetailsBottomSheet
import com.kryptonictest.databinding.FragmentFavoritesBinding
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.utils.interfaces.OnChangeRepositoryFavoriteListener
import com.kryptonictest.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    private val viewModel: FavoritesViewModel by viewModels()
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
        if (!isAdded) return
        val showRepo = RepositoryDetailsBottomSheet.showRepositoryDetailsBottomSheet(item)
        showRepo.setOnItemClickListener(object : OnChangeRepositoryFavoriteListener {
            override fun onChange(item: GithubRepo, isRemove: Boolean) {
                viewModel.addOrRemoveItemFromFavorite(item, isRemove)
            }
        })
        showRepo.show(childFragmentManager, javaClass.canonicalName)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllRepositories()
    }
}