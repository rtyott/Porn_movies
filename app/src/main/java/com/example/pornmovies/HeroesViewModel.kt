package com.example.pornmovies

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class HeroesViewModel: ViewModel(){
    var heroesStat: MutableLiveData<List<HeroListEntity>> = MutableLiveData()
    
    fun getHeroesLocal(context: Context) = HeroRepository.getData(context)
    fun getHeroesRemote(){
        viewModelScope.launch {
            heroesStat.postValue(Retrofit.getRetrofitInstance().getHeroesStats().body())
        }
    }
    fun addHero(hero:HeroListEntity){
        viewModelScope.launch {
           HeroRepository.addHero(hero)
        }
    }
}