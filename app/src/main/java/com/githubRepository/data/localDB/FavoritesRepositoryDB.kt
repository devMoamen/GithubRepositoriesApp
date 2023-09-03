package com.githubRepository.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.githubRepository.domain.model.githubList.GithubRepo

@Database(entities = [GithubRepo::class], version = 1)
abstract class FavoritesRepositoryDB : RoomDatabase() {
    abstract fun favoriteRepositoryDao(): FavoriteRepositoryDao
}