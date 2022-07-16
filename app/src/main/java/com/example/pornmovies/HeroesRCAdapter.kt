package com.example.pornmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pornmovies.databinding.HeroLayoutBinding
import com.squareup.picasso.Picasso

class HeroesRCAdapter(var heroList:List<IsFavoriteEntity>): RecyclerView.Adapter<HeroesRCAdapter.HeroesHolder>() {
    lateinit var myListener: FavClickListener
    class HeroesHolder(var binding: HeroLayoutBinding,listener: FavClickListener): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesHolder {
        var binding = HeroLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HeroesHolder(binding,myListener)
    }

    override fun onBindViewHolder(holder: HeroesHolder, position: Int) {
        holder.binding.textName.text = heroList[position].hero.localized_name
        Picasso.get().load(heroList[position].hero.img).into(holder.binding.imageImg)
        Picasso.get().load(heroList[position].hero.icon).into(holder.binding.imageIcon)
        if(heroList[position].favorite){
            holder.binding.favButton.setImageResource(R.drawable.ic_fav)
        }
        else{
            holder.binding.favButton.setImageResource(R.drawable.ic_not_fav)
        }
        holder.binding.favButton.setOnClickListener {
            myListener.OnClick(heroList[position])
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }
    interface FavClickListener{
        fun OnClick(isFavoriteEntity: IsFavoriteEntity)
    }
    fun setOnFavClickListener(listener: FavClickListener){
        myListener = listener
    }
}