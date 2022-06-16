package com.umbertoemonds.dharmonie.data.models

import com.google.gson.annotations.SerializedName

data class Projet (

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String

)