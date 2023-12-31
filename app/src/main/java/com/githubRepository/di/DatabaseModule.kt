package com.githubRepository.di

import android.content.Context
import androidx.room.Room
import com.githubRepository.data.localDB.FavoriteRepositoryDao
import com.githubRepository.data.localDB.FavoritesRepositoryDB

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {
    @Provides
    fun provideFavoritesRepositoryDB(appDatabase: FavoritesRepositoryDB): FavoriteRepositoryDao {
        return appDatabase.favoriteRepositoryDao()
    }

    @Provides
    @ViewModelScoped
    fun provideAppDatabase(@ApplicationContext appContext: Context): FavoritesRepositoryDB {
        return Room.databaseBuilder(
            appContext,
            FavoritesRepositoryDB::class.java,
            " FavoritesRepository"
        ).allowMainThreadQueries().build()
    }
}