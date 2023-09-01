package com.kryptonictest.app.ui.bottomSheet.repositoryDetailsBS

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kryptonictest.app.bases.BaseViewModel
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.domain.repository.FavoriteRepositoryRepo
import com.kryptonictest.utils.general.SingleLiveEvent

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(private val favoriteRepositoryDao: FavoriteRepositoryRepo) :
    BaseViewModel() {

     var githubRepoItem: GithubRepo? = null

    val itemFavoriteChanged = SingleLiveEvent<Boolean>()
    private val openRepoOnGitHub = SingleLiveEvent<GithubRepo>()
    fun getOpenRepoOnGitHub(): SingleLiveEvent<GithubRepo> {
        return openRepoOnGitHub
    }

    val imgUrl = MutableLiveData<String>().apply { value = "" }
    val repoTitle = MutableLiveData<String>().apply { value = "" }
    val repoLanguage = MutableLiveData<String>().apply { value = "" }
    val repoDescription = MutableLiveData<String>().apply { value = "" }
    val repoCreateAt = MutableLiveData<String>().apply { value = "" }
    val repoForkCount = MutableLiveData<String>().apply { value = "" }
    val repoLikeCount = MutableLiveData<String>().apply { value = "" }
    val isInFavorite = MutableLiveData<Boolean>().apply { value = false }
    val favoriteBtnTitle = MutableLiveData<String>().apply { value = "" }

    fun fillData(githubRepo: GithubRepo?) {
        this.githubRepoItem = githubRepo
        if (githubRepo == null) return

        with(githubRepo) {
            imgUrl.value = owner?.avatar_url
            repoTitle.value = full_name
            repoLanguage.value = language
            repoDescription.value = getDescriptionStr()
            repoForkCount.value = "Fork: $forks_count"
            repoLikeCount.value = stargazers_count.toString()
            repoCreateAt.value = getCreateAtStr()
            checkAddedToFavorite(id)
        }
    }

    private fun checkAddedToFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepositoryDao.getFavoriteRepositoryById(id).onStart {}.collect {
                if (it != null) {
                    isInFavorite.postValue(it.id == id)
                    favoriteBtnTitle.postValue("Remove favorite")
                } else {
                    isInFavorite.postValue(false)
                    favoriteBtnTitle.postValue("Add to favorite")
                }
            }
        }
    }

    fun openRepoOnGitHub() {
        githubRepoItem?.let {
            openRepoOnGitHub.value = it
        }
    }

    fun clickAddToFavorite() {
        githubRepoItem?.let {
            viewModelScope.launch(Dispatchers.IO) {
                if (isInFavorite.value!!) {
                    favoriteRepositoryDao.deleteFavoriteRepository(it.id)
                    favoriteBtnTitle.postValue("Add to favorite")
                    isInFavorite.postValue(false)
                    itemFavoriteChanged.postValue(false)
                } else {
                    favoriteRepositoryDao.insertFavoriteRepository(it)
                    favoriteBtnTitle.postValue("Remove favorite")
                    isInFavorite.postValue(true)
                    itemFavoriteChanged.postValue(true)
                }
            }
        }
    }
}