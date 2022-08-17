package com.cheese.animoapp.util

object Constants {
    const val HOME_FRAGMENT = "HOME_FRAGMENT"
    const val DETAILS_FRAGMENT = "DETAILS_FRAGMENT"
    const val KEY_ID = "ID"
    const val ANIME_NOT_FOUND = "anime not found"



    object ApiKey{
        const val SCHEMA = "https"
        const val HOST = "ghibliapi.herokuapp.com"
        const val PATH_FILMS = "films"
        const val PATH_SPECIES = "species"
        const val ACCEPT ="Accept"
        const val TYPE ="application/json"

        object Params {
            const val ID = "id"
            const val TITLE = "title"
            const val Original_Title_Romanised = "original_title_romanised"

        }
    }
}