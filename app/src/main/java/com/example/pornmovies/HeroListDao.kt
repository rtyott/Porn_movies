package com.example.pornmovies

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface HeroListDao {
    @Query("SELECT * FROM is_favorite_table")
    fun getEverything(): LiveData<List<IsFavoriteEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insersFav(isFavoriteEntity: IsFavoriteEntity)
    @Update
    suspend fun update(isFavoriteEntity: IsFavoriteEntity)
}