package com.kryptonictest.di

import android.content.Context
import androidx.room.Room
import com.kryptonictest.data.localDB.FavoriteRepositoryDao
import com.kryptonictest.data.localDB.FavoritesRepositoryDB
import com.kryptonictest.data.repository.GithubRepositoryFavoritesImp
import com.kryptonictest.data.repository.GithubRepositoryRepImp
import com.kryptonictest.domain.repository.FavoriteRepositoryRepo
import com.kryptonictest.domain.repository.GithubRepositoryRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DatabaseModule {
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