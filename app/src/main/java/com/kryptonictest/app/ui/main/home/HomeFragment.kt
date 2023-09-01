package com.kryptonictest.app.ui.main.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.kryptonictest.R
import com.kryptonictest.app.bases.BaseFragment
import com.kryptonictest.app.ui.bottomSheet.repositoryDetailsBS.RepositoryDetailsBottomSheet
import com.kryptonictest.app.ui.bottomSheet.repositoryFilterBS.RepositoryFilterBottomSheet
import com.kryptonictest.databinding.FragmentHomeBinding
import com.kryptonictest.domain.model.general.RepositoryFilterType
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.utils.interfaces.OnSelectRepositoryFilterListener
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
        setupMenu()

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

        RepositoryDetailsBottomSheet.showRepositoryDetailsBottomSheet(item)
            .show(childFragmentManager, javaClass.canonicalName)
    }

    private fun showFilterBottomSheet() {
        if (!isAdded) return

        val showDialog = RepositoryFilterBottomSheet.showRepositoryFilterBottomSheet()
        showDialog.setOnItemClickListener(object : OnSelectRepositoryFilterListener {
            override fun onSelectFilter(filterType: RepositoryFilterType) {
                viewModel.onSelectFilter(filterType)
            }
        })
        showDialog.show(childFragmentManager, javaClass.canonicalName)
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_home, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.item_filter) {
                    showFilterBottomSheet()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}