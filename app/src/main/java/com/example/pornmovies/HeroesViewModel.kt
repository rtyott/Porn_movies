package com.example.pornmovies

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class HeroesViewModel: ViewModel(){
    var sorting:MutableLiveData<String> = MutableLiveData()
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
    fun getSorting(context: Context) {
            viewModelScope.launch {
            sorting.postValue(SettingsRepository.getSort(context))
        }
    }
    fun putSorting(string: String){
        viewModelScope.launch {
        SettingsRepository.putSort(string)
        }
    }
}