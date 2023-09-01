package com.kryptonictest.app.ui.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kryptonictest.app.adapters.loadMoreAdapter.LoadingMoreAdapter
import com.kryptonictest.utils.interfaces.OnRepositoryItemClickListener
import com.kryptonictest.app.adapters.repositoryAdapter.RepositoryListAdapter
import com.kryptonictest.app.bases.BaseViewModel
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.domain.repository.FavoriteRepositoryRepo
import com.kryptonictest.domain.repository.GithubRepositoryRepo
import com.kryptonictest.network.ApiParamsConstant.PARAM_ORDER
import com.kryptonictest.network.ApiParamsConstant.PARAM_ORDER_DESC
import com.kryptonictest.network.ApiParamsConstant.PARAM_PAGE
import com.kryptonictest.network.ApiParamsConstant.PARAM_PER_PAGE
import com.kryptonictest.network.ApiParamsConstant.PARAM_PER_PAGE_VALUE
import com.kryptonictest.network.ApiParamsConstant.PARAM_Q
import com.kryptonictest.network.ApiParamsConstant.PARAM_SORT
import com.kryptonictest.network.ApiParamsConstant.PARAM_SORT_STARS
import com.kryptonictest.utils.general.SingleLiveEvent
import com.kryptonictest.utils.interfaces.OnLoadingMoreListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val favoriteRepositoryDao: FavoriteRepositoryRepo) :
    BaseViewModel(), OnRepositoryItemClickListener {

    private val openRepoDetails = SingleLiveEvent<GithubRepo>()
    fun getOpenRepoDetails(): SingleLiveEvent<GithubRepo> {
        return openRepoDetails
    }

    val showEmptyView = MutableLiveData<Boolean>().apply { value = false }
    val githubRepoAdapter = MutableLiveData<RepositoryListAdapter>().apply {
        value = RepositoryListAdapter().apply {
            setOnItemClickListener(this@FavoritesViewModel)
        }
    }

    fun getAllRepositories() {
        isLoading.postValue(true)
        getRepositories()
    }

    private fun resetData() {
        githubRepoAdapter.value!!.removeAll()
    }

    private fun getRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepositoryDao.getAllFavoriteRepositories().onStart {
                showEmptyView.postValue(false)
            }.catch {
                isLoading.postValue(false)
                showEmptyView.postValue(true)
                showErrorMessage.postValue(it.message)
            }.collect {
                isRefreshLoading.postValue(false)
                isLoading.postValue(false)
                if (it.isEmpty()) {
                    showEmptyView.postValue(true)
                }
                viewModelScope.launch {
                    handleGithubRepositoryResult(it as ArrayList<GithubRepo>)
                }
            }
        }
    }

    private fun handleGithubRepositoryResult(items: ArrayList<GithubRepo>) {
        githubRepoAdapter.value!!.addItems(items)
    }

    override fun openRepositoryDetails(item: GithubRepo) {
        openRepoDetails.value = item
    }

    val onRefreshingListener = SwipeRefreshLayout.OnRefreshListener {
        resetData()
        getRepositories()
    }


    fun addOrRemoveItemFromFavorite(githubRepo: GithubRepo, isRemove: Boolean) {
        if (isRemove) {
            viewModelScope.launch(Dispatchers.IO) {
                favoriteRepositoryDao.deleteFavoriteRepository(githubRepo.id)
            }
            githubRepoAdapter.value!!.removeItem(githubRepo)
        } else {
            githubRepoAdapter.value!!.addItem(githubRepo)
        }
        checkIsEmptyList()
    }

    private fun checkIsEmptyList() {
        showEmptyView.postValue(githubRepoAdapter.value!!.isEmpty())
    }
}