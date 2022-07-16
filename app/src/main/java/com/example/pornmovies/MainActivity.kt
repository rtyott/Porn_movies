package com.example.pornmovies

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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
        binding.btn.setOnClickListener {
            getFirstData()
        }
        getFirstData()
    }

    private fun getFirstData() {
        when(viewModel.getSorting(this)){
            "legs"->{
                viewModel.getHeroesLegs(this).observe(this, Observer { legs ->
                    sortReceivedData(legs)
                })
            }
            "name"->{
            viewModel.getHeroesName(this).observe(this, Observer { name ->
                sortReceivedData(name)
            })}
            }

    }
    fun sortReceivedData(it3:List<IsFavoriteEntity>){
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        var searchMenuItem = menu?.findItem(R.id.search_view_id)
        var menuitem = searchMenuItem!!.actionView as SearchView
        menuitem.queryHint = "kf"
        menuitem.isIconified = false
        var closeButtonId = resources.getIdentifier("android:id/search_close_btn",null,null)
        var closebtn = menuitem.findViewById<ImageView>(closeButtonId)
        closebtn.setImageResource(R.drawable.ic_sort_icon)
        closebtn.setOnClickListener {
            showDialog()
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun showDialog() {
        var bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        var binding1:SortBottomSheetBinding = SortBottomSheetBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding1.root)
        binding1.btnSortLegs.setOnClickListener {
            viewModel.putSorting("legs")
            getFirstData()
            bottomSheetDialog.dismiss()
        }
        binding1.btnSortName.setOnClickListener {
            viewModel.putSorting("name")
            getFirstData()
            bottomSheetDialog.dismiss()

        }
        bottomSheetDialog.window?.setBackgroundDrawableResource(Color.TRANSPARENT)
        bottomSheetDialog.show()
    }
}