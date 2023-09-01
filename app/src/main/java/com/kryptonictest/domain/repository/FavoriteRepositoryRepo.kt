package com.kryptonictest.domain.repository


import com.kryptonictest.domain.model.githubList.GithubRepo
import kotlinx.coroutines.flow.Flow

interface FavoriteRepositoryRepo {
    fun getAllFavoriteRepositories(): Flow<List<GithubRepo>>
    fun getFavoriteRepositoriesById(mId: Int): Flow<GithubRepo>
    fun insertFavoriteRepository(githubRepo: GithubRepo)
    fun deleteFavoriteRepository(mId: Int)
}