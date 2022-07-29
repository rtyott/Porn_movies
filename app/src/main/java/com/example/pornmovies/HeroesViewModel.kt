package com.example.pornmovies

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroesViewModel: ViewModel(){
    var _sorting:MutableLiveData<String> = MutableLiveData()
    var sorting:LiveData<String> = _sorting
    var _heroesStat: MutableLiveData<List<HeroListEntity>> = MutableLiveData()
    var heroesStats:LiveData<List<HeroListEntity>> = _heroesStat
    private var _heroName: MutableLiveData<List<IsFavoriteEntity>> = MutableLiveData()
    var heroName:LiveData<List<IsFavoriteEntity>> = _heroName
    private var _heroLegs:MutableLiveData<List<IsFavoriteEntity>> = MutableLiveData()
    var heroLegs:LiveData<List<IsFavoriteEntity>> = _heroLegs

    
    fun getHeroesName(context: Context) =
        viewModelScope.launch(Dispatchers.IO) {
            _heroName.postValue(HeroRepository.getDataName(context))
        }
    fun getHeroesRemote(){
        viewModelScope.launch {
            _heroesStat.postValue(Retrofit.getRetrofitInstance().getHeroesStats().body())
        }
    }
    fun getHeroesLegs(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _heroLegs.postValue(HeroRepository.getDataLegs(context))
    }
    }
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
    fun createPrefs(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            SettingsRepository.initPrefs(context)
        }
    }
    fun getSorting() {
            viewModelScope.launch {
            _sorting.postValue(SettingsRepository.getSort())
        }
    }
    fun putSorting(string: String){
        viewModelScope.launch {
        SettingsRepository.putSort(string)
        }
    }
}