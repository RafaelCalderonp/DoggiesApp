package com.example.model.remote

import com.example.model.remote.pojo.WrapperBreed
import com.example.model.remote.pojo.WrapperImages
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @GET("breeds/list/")
    fun fetchBreedList(): Call<WrapperBreed>

    @GET("breeds/list/")
    suspend fun fetchBreedListCorutinas(): Response<WrapperBreed>

    @GET("breed/{breed}/images")
    fun fetchImagesList(breed: String): Call<WrapperImages>
}