package com.cheese.animoapp.util.interfaces

import com.cheese.animoapp.data.models.Films
import com.cheese.animoapp.data.models.NewModelItem

interface ItemListener {
    fun onClickItem(anime: NewModelItem)
}