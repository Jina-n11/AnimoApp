package com.cheese.animoapp.data.models

import com.google.gson.annotations.SerializedName

data class Species(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("classification")
    val classification: String,

    @SerializedName("films")
    val films: List<String>,

    @SerializedName("eye_colors")
    val eyeColors: String,

    @SerializedName("hair_colors")
    val hairColors: String,

    val people: List<String>,

    val url: String

)
