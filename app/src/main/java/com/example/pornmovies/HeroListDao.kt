package com.example.pornmovies

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface HeroListDao {
    @Query("SELECT * FROM is_favorite_table ORDER BY localized_name")
    fun getEverythingName(): List<IsFavoriteEntity>
    @Query("SELECT * FROM is_favorite_table ORDER BY legs")
    fun getEverythingLegs(): List<IsFavoriteEntity>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insersFav(isFavoriteEntity: IsFavoriteEntity)
    @Update()
    suspend fun update(isFavoriteEntity: IsFavoriteEntity)
}