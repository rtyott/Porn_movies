package com.example.pornmovies

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pornmovies.HeroesRCAdapter.FavClickListener
import com.example.pornmovies.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: HeroesViewModel
    lateinit var adapter1:HeroesRCAdapter
    var list:MutableList<HeroListEntity>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(HeroesViewModel::class.java)
        binding.btn.setOnClickListener {
            getFirstData()

        }
    }

    private fun getFirstData() {
        viewModel.getHeroesLocal(this).observe(this, Observer { it3 ->
            if(it3.size!=0){
                var kk = it3

                adapter1 = HeroesRCAdapter(it3)
                binding.heroRecycler.layoutManager = GridLayoutManager(this,2)
                binding.heroRecycler.adapter = adapter1
                adapter1.notifyDataSetChanged()
                adapter1.setOnFavClickListener(object : FavClickListener{
                    override fun OnClick(isFavoriteEntity: IsFavoriteEntity) {
                        isFavoriteEntity.favorite = !isFavoriteEntity.favorite
                        viewModel.update(isFavoriteEntity)
                        getFirstData()
                    }
                })
            }
            else{
                viewModel.getHeroesRemote()
                viewModel.heroesStat.observe(this, Observer { heroList ->
                    heroList.map{
                        it.img = "${Retrofit.baseImg}${it.img}"
                        it.icon = "${Retrofit.baseImg}${it.icon}"

                        viewModel.addFav(IsFavoriteEntity(favorite = false,hero = it))

                    }

                })
            }
        })
    }
}