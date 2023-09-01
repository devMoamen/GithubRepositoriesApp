package com.kryptonictest.di

import com.kryptonictest.data.repository.GithubRepositoryFavoritesImp
import com.kryptonictest.data.repository.GithubRepositoryRepImp
import com.kryptonictest.domain.repository.FavoriteRepositoryRepo
import com.kryptonictest.domain.repository.GithubRepositoryRepo
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

//    @Binds
//    @ViewModelScoped
//    abstract fun provideFavoriteRepositoryRepo(githubRepositoryFavoritesImp: GithubRepositoryFavoritesImp): FavoriteRepositoryRepo
}