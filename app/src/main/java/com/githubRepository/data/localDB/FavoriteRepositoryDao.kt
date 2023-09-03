package com.githubRepository.data.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.githubRepository.domain.model.githubList.GithubRepo

@Dao
interface FavoriteRepositoryDao {

    @Query("SELECT * FROM GithubRepo")
    fun getAllFavoriteRepositories(): List<GithubRepo>

    @Query("SELECT * FROM GithubRepo where id =:mId")
    fun getFavoriteRepositoryById(mId: Int): GithubRepo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRepository(githubRepo: GithubRepo)

    @Query("DELETE  FROM GithubRepo where id =:mId")
    fun deleteFavoriteRepository(mId: Int)
}