package com.umbertoemonds.dharmonie.data.models

import com.google.gson.annotations.SerializedName

data class Grille (

    @SerializedName("id")
    val id: Int,

    @SerializedName("nom")
    val nom: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("tempo")
    val tempo: Int,

    @SerializedName("noteId")
    val noteId: Int,

    @SerializedName("modeId")
    val modeId: Int,

    @SerializedName("projet")
    val projet: Projet,

    @SerializedName("styleId")
    val styleId: Int

)