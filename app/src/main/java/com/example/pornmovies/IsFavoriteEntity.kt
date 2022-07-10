package com.example.pornmovies

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "is_favorite_table")
data class IsFavoriteEntity(
    @PrimaryKey(autoGenerate = true) var favId: Int = 0,
    var favorite:Boolean,
    @Embedded
    var hero:HeroListEntity
)
