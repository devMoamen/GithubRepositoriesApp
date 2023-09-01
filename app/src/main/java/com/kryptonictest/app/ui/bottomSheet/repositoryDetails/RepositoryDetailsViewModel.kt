package com.kryptonictest.app.ui.bottomSheet.repositoryDetails

import androidx.lifecycle.MutableLiveData
import com.kryptonictest.app.bases.BaseViewModel
import com.kryptonictest.data.localDB.FavoriteRepositoryDao
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.utils.general.SingleLiveEvent

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(private val favoriteRepositoryDao: FavoriteRepositoryDao) :
    BaseViewModel() {

    private var githubRepoItem: GithubRepo? = null

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
        val itemFavorite = favoriteRepositoryDao.getFavoriteRepositoriesById(id)
        if (itemFavorite != null) {
            isInFavorite.value = itemFavorite.id == id
        } else {
            isInFavorite.value = false
        }

        favoriteBtnTitle.value =
            if (isInFavorite.value!!) "Remove favorite" else "Add to favorite"

    }

    fun openRepoOnGitHub() {
        githubRepoItem?.let {
            openRepoOnGitHub.value = it
        }
    }

    fun clickAddToFavorite() {
        githubRepoItem?.let {
            if (isInFavorite.value!!) {
                favoriteRepositoryDao.deleteFavoriteRepository(it.id)
            } else {
                favoriteRepositoryDao.insertFavoriteRepository(it)
            }

            checkAddedToFavorite(it.id)
        }
    }


}