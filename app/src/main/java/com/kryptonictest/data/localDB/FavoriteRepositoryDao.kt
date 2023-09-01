package com.kryptonictest.data.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kryptonictest.domain.model.githubList.GithubRepo
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRepositoryDao {

    @Query("SELECT * FROM GithubRepo")
    fun getAllFavoriteRepositories(): List<GithubRepo>

    @Query("SELECT * FROM GithubRepo where id =:mId")
    fun getFavoriteRepositoriesById(mId: Int): GithubRepo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRepository(githubRepo: GithubRepo)

    @Query("DELETE  FROM GithubRepo where id =:mId")
    fun deleteFavoriteRepository(mId: Int)
}