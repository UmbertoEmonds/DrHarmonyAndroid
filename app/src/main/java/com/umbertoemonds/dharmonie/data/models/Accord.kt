package com.umbertoemonds.dharmonie.data.models

import com.google.gson.annotations.SerializedName

data class Accord (

    @SerializedName("id")
    val id: Int,

    @SerializedName("position")
    val position: Int,

    @SerializedName("grilleId")
    val grilleId: Int,

    @SerializedName("demiTons")
    val demiTons: List<Int>

)