package com.kryptonictest.data.repository

import com.kryptonictest.data.localDB.FavoriteRepositoryDao
import com.kryptonictest.domain.model.githubList.GithubRepo
import com.kryptonictest.domain.repository.FavoriteRepositoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepositoryFavoritesImp @Inject constructor(
    private val favoriteRepositoryDao: FavoriteRepositoryDao
) : FavoriteRepositoryRepo {

    override fun getAllFavoriteRepositories(): Flow<List<GithubRepo>> =
        flow {
            emit(favoriteRepositoryDao.getAllFavoriteRepositories())
        }.flowOn(Dispatchers.IO)

    override fun getFavoriteRepositoriesById(mId: Int): Flow<GithubRepo> =
        flow {
            emit(favoriteRepositoryDao.getFavoriteRepositoriesById(mId))
        }.flowOn(Dispatchers.IO)

    override fun insertFavoriteRepository(githubRepo: GithubRepo) =
        favoriteRepositoryDao.insertFavoriteRepository(githubRepo)

    override fun deleteFavoriteRepository(mId: Int) =
        favoriteRepositoryDao.deleteFavoriteRepository(mId)
}