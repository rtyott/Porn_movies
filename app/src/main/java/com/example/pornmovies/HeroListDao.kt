package com.example.pornmovies

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface HeroListDao {
    @Query("SELECT * FROM hero_list_entity_table")
    fun getEverything(): LiveData<List<HeroListEntity>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(heroListEntity: HeroListEntity)
}