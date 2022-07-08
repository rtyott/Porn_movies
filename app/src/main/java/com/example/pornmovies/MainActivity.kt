package com.example.pornmovies

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pornmovies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: HeroesViewModel
    lateinit var adapter1:HeroesRCAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(HeroesViewModel::class.java)
        binding.btn.setOnClickListener {
        viewModel.getHeroesLocal(this).observe(this, Observer { it3 ->
            if(it3.size!=0){
                var kk = it3
                adapter1 = HeroesRCAdapter(it3)
                binding.heroRecycler.layoutManager = GridLayoutManager(this,3)
                binding.heroRecycler.adapter = adapter1
            }
            else{
                viewModel.getHeroesRemote()
                viewModel.heroesStat.observe(this, Observer { heroList ->
                    heroList.map {
                        it.icon = "${Retrofit.baseImg}${it.icon}"
                        it.img = "${Retrofit.baseImg}${it.img}"
                    }
                    heroList.map { viewModel.addHero(it) }

                })
            }
        })

        }
    }
}