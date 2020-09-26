package com.example.model.remote.pojo


import com.google.gson.annotations.SerializedName

data class WrapperBreed(
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String
)