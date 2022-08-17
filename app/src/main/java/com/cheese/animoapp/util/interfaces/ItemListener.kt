package com.cheese.animoapp.util.interfaces

import com.cheese.animoapp.data.models.AnimeFilm

interface ItemListener {
    fun onClickItem(anime: AnimeFilm)
}