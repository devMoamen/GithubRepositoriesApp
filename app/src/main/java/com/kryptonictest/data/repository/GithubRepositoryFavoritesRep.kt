package com.kryptonictest.data.repository

import com.kryptonictest.data.localDB.FavoriteRepositoryDao
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.domain.repository.FavoriteRepositoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepositoryFavoritesRep @Inject constructor(
    private val favoriteRepositoryDao: FavoriteRepositoryDao
) {
    fun getAllFavoriteRepositories() = favoriteRepositoryDao.getAllFavoriteRepositories()

    fun getFavoriteRepositoriesById(mId: Int) =
        favoriteRepositoryDao.getFavoriteRepositoriesById(mId)

    fun insertFavoriteRepository(githubRepo: GithubRepo) =
        favoriteRepositoryDao.insertFavoriteRepository(githubRepo)

    fun deleteFavoriteRepository(mId: Int) =
        favoriteRepositoryDao.deleteFavoriteRepository(mId)
}