package com.githubRepository.app.ui.main.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.githubRepository.utils.interfaces.OnRepositoryItemClickListener
import com.githubRepository.app.adapters.repositoryAdapter.RepositoryListAdapter
import com.githubRepository.app.bases.BaseViewModel
import com.githubRepository.domain.model.githubList.GithubRepo
import com.githubRepository.domain.repository.FavoriteRepositoryRepo
import com.githubRepository.utils.general.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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