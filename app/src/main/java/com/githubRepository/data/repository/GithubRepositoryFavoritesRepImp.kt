package com.githubRepository.data.repository

import com.githubRepository.data.localDB.FavoriteRepositoryDao
import com.githubRepository.domain.model.githubList.GithubRepo
import com.githubRepository.domain.repository.FavoriteRepositoryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepositoryFavoritesRepImp @Inject constructor(
    private val favoriteRepositoryDao: FavoriteRepositoryDao
) : FavoriteRepositoryRepo {
    override fun getAllFavoriteRepositories(): Flow<List<GithubRepo>> =
        flow {
            emit(favoriteRepositoryDao.getAllFavoriteRepositories())
        }.flowOn(Dispatchers.IO)

    override fun getFavoriteRepositoryById(mId: Int): Flow<GithubRepo?> =
        flow {
            emit(favoriteRepositoryDao.getFavoriteRepositoryById(mId))
        }.flowOn(Dispatchers.IO)

    override fun insertFavoriteRepository(githubRepo: GithubRepo) =
        favoriteRepositoryDao.insertFavoriteRepository(githubRepo)

    override fun deleteFavoriteRepository(mId: Int) {
        favoriteRepositoryDao.deleteFavoriteRepository(mId)
    }
}