package com.example.pornmovies

import android.content.Context
import androidx.lifecycle.LiveData

object HeroRepository {
     var heroListDatabase:HeroListDatabase? = null
     var liveData: LiveData<List<HeroListEntity>>? = null
     fun getDataName(context: Context): LiveData<List<IsFavoriteEntity>> {
          if(heroListDatabase == null){
               heroListDatabase = HeroListDatabase.createDatabase(context)
          }
          return heroListDatabase!!.getListDao().getEverythingName()
     }
     fun getDataLegs(context: Context): LiveData<List<IsFavoriteEntity>> {
          if(heroListDatabase == null){
               heroListDatabase = HeroListDatabase.createDatabase(context)
          }
          return heroListDatabase!!.getListDao().getEverythingLegs()
     }

     suspend fun addFav(isFavoriteEntity: IsFavoriteEntity){
          heroListDatabase!!.getListDao().insersFav(isFavoriteEntity)
     }
     suspend fun updateFav(isFavoriteEntity: IsFavoriteEntity){
          heroListDatabase!!.getListDao().update(isFavoriteEntity)
     }
}