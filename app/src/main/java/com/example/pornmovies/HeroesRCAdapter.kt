package com.example.pornmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pornmovies.databinding.HeroLayoutBinding
import com.squareup.picasso.Picasso

class HeroesRCAdapter(var heroList:List<HeroListEntity>): RecyclerView.Adapter<HeroesRCAdapter.HeroesHolder>() {
    class HeroesHolder(var binding: HeroLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesHolder {
        var binding = HeroLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HeroesHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesHolder, position: Int) {
        holder.binding.textName.text = heroList[position].localized_name
        Picasso.get().load(heroList[position].img).into(holder.binding.imageImg)
        Picasso.get().load(heroList[position].icon).into(holder.binding.imageIcon)

    }

    override fun getItemCount(): Int {
        return heroList.size
    }
}