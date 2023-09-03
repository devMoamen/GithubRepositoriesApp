package com.githubRepository.domain.repository


import com.githubRepository.domain.model.githubList.GithubRepo
import kotlinx.coroutines.flow.Flow

interface FavoriteRepositoryRepo {
    fun getAllFavoriteRepositories(): Flow<List<GithubRepo>>
    fun getFavoriteRepositoryById(mId: Int): Flow<GithubRepo?>
    fun insertFavoriteRepository(githubRepo: GithubRepo)
    fun deleteFavoriteRepository(mId: Int)
}