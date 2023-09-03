package com.githubRepository.di

import com.githubRepository.data.repository.GithubRepositoryFavoritesRepImp
import com.githubRepository.data.repository.GithubRepositoryRepImp
import com.githubRepository.domain.repository.FavoriteRepositoryRepo
import com.githubRepository.domain.repository.GithubRepositoryRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun provideGithubRepositoryRepo(githubRepositoryRepImp: GithubRepositoryRepImp): GithubRepositoryRepo

    @Binds
    @ViewModelScoped
    abstract fun provideFavoriteRepositoryRepo(githubRepositoryFavoritesRepImp: GithubRepositoryFavoritesRepImp): FavoriteRepositoryRepo
}