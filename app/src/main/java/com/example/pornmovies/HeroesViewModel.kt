package com.example.pornmovies

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class HeroesViewModel: ViewModel(){
    var heroesStat: MutableLiveData<List<HeroListEntity>> = MutableLiveData()
    
    fun getHeroesName(context: Context) = HeroRepository.getDataName(context)
    fun getHeroesRemote(){
        viewModelScope.launch {
            heroesStat.postValue(Retrofit.getRetrofitInstance().getHeroesStats().body())
        }
    }
    fun getHeroesLegs(context: Context) = HeroRepository.getDataLegs(context)

    fun addFav(isFavoriteEntity: IsFavoriteEntity){
        viewModelScope.launch {
            HeroRepository.addFav(isFavoriteEntity)
        }
    }
    fun update(isFavoriteEntity: IsFavoriteEntity){
        viewModelScope.launch {
            HeroRepository.updateFav(isFavoriteEntity)
        }
    }
    fun getSorting(context: Context): String {
        return SettingsRepository.getSort(context)
    }
    fun putSorting(string: String){
        SettingsRepository.putSort(string)
    }
}