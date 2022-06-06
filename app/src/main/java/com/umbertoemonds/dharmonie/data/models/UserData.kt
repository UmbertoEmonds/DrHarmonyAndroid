package com.umbertoemonds.dharmonie.data.models

import com.google.gson.annotations.SerializedName

data class UserData (

    @SerializedName("id")
    val id: Int,

    @SerializedName("firstname")
    val firstname: String,

    @SerializedName("lastname")
    val lastname: String,

    @SerializedName("email")
    val email: String

)