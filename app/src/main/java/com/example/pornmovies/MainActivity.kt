package com.example.pornmovies

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pornmovies.HeroesRCAdapter.FavClickListener
import com.example.pornmovies.databinding.ActivityMainBinding
import com.example.pornmovies.databinding.SortBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

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
        var closeButtonId2 = resources.getIdentifier("android:id/search_close_btn",null,null)
        var clos= binding.searchView.findViewById<ImageView>(closeButtonId2)
        clos.setImageResource(R.drawable.ic_sort_icon)
        clos.setOnClickListener {
            showDialog()
        }
        getFirstData()
    }

    private fun getFirstData() {
        viewModel.getSorting(this)
        viewModel.sorting.observe(this) {sort->
            fetchData(sort)
        }

    }
    fun sortReceivedData(it3:List<IsFavoriteEntity>){
        if(it3.isNotEmpty()){
            adapter1 = HeroesRCAdapter(it3)
            binding.heroRecycler.layoutManager = GridLayoutManager(this,2)
            binding.heroRecycler.adapter = adapter1
            adapter1.notifyDataSetChanged()
            adapter1.setOnFavClickListener(object : FavClickListener{
                override fun OnClick(isFavoriteEntity: IsFavoriteEntity) {
                    isFavoriteEntity.favorite = !isFavoriteEntity.favorite
                    viewModel.update(isFavoriteEntity)
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
    }



    private fun showDialog() {
        var bottomSheetDialog = BottomSheetDialog(this,R.style.DialogCustomTheme)
        bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        var binding1: SortBottomSheetBinding = SortBottomSheetBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding1.root)
        binding1.btnSortLegs.setOnClickListener {
            viewModel.putSorting("legs")
            bottomSheetDialog.dismiss()
            fetchData("legs")
        }
        binding1.btnSortName.setOnClickListener {
            viewModel.putSorting("name")
            bottomSheetDialog.dismiss()
            fetchData("name")
        }
        bottomSheetDialog.window!!.attributes.windowAnimations = R.style.DialogAnim
        bottomSheetDialog.show()
    }
    fun fetchData(string: String){
        when(string) {
            "legs" -> {
                viewModel.getHeroesLegs(this).observe(this){ legs ->
                    viewModel.getHeroesName(this).removeObservers(this)
                    sortReceivedData(legs)
                }

            }
            "name" -> {
                viewModel.getHeroesName(this).observe(this){ name ->
                    viewModel.getHeroesLegs(this).removeObservers(this)
                    sortReceivedData(name)
                }
            }
        }
    }
}