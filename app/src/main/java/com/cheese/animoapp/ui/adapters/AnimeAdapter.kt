package com.cheese.animoapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheese.animoapp.R
import com.cheese.animoapp.data.models.NewModel
import com.cheese.animoapp.databinding.AnimeItemBinding
import com.cheese.animoapp.util.interfaces.ItemListener
import com.cheese.animoapp.util.toLengthOfTime


class AnimeAdapter(private val animes: NewModel, private val itemListener: ItemListener):
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.anime_item,parent,false)
        return  AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime =animes[position]

        holder.binding.apply {
            Glide.with(root).load(anime.image).into(animeImageI)
            Glide.with(root).load(anime.image).into(mainAnimeImageItem)
            animeName.text = anime.originalTitleRomanised
            description.text = anime.description
            rate.text = anime.rateScore
            released.text = anime.releaseDate
            length.text = anime.runningTime.toLengthOfTime()

            animeItem.setOnClickListener {
                itemListener.onClickItem(anime)
            }
        }
    }

    override fun getItemCount()=animes.size

    class AnimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding= AnimeItemBinding.bind(itemView)
    }
}
