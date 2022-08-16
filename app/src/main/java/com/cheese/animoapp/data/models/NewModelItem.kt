package com.cheese.animoapp.data.models

import com.google.gson.annotations.SerializedName

data class NewModelItem(

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("original_title_romanised")
    val originalTitleRomanised: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("movie_banner")
    val movieBanner: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("director")
    val director: String,

    @SerializedName("producer")
    val producer: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("running_time")
    val runningTime: String,

    @SerializedName("rt_score")
    val rateScore: String,

    @SerializedName("species")
    val species: List<Species>,



    val locations: List<String>,

    val movie_banner: String,

    val original_title: String,

    val rt_score: String,

    val running_time: String,

    val people: List<String>,

    val release_date: String,




    val url: String,
    val vehicles: List<String>


)
