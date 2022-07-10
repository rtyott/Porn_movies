package com.example.pornmovies

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [IsFavoriteEntity::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HeroListDatabase: RoomDatabase() {
    abstract fun getListDao(): HeroListDao
    companion object {
        @Volatile
        var instance: HeroListDatabase? = null
        fun createDatabase(context: Context): HeroListDatabase {
            if(instance != null) return instance!!
            synchronized(this){
            instance = Room
                .databaseBuilder(context.applicationContext, HeroListDatabase::class.java, "article_db.db")
                .fallbackToDestructiveMigration()
                .build()
            return instance!!
            }
        }
    }

}
