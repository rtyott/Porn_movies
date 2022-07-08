package com.example.pornmovies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "hero_list_entity_table")
data class HeroListEntity(
    @PrimaryKey
    var id: Int,
    var attack_type: String,
    var legs: Int,
    var localized_name: String,
    var name: String,
    var primary_attr: String,
    var roles: List<String>,
    var img: String,
    var icon: String
    )
