package com.example.model.remote

import com.example.model.remote.pojo.WrapperBreed
import com.example.model.remote.pojo.WrapperImages
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("breeds/list/")
    suspend fun fetchBreedList(): Response<WrapperBreed>

    @GET("breed/{breed}/images")
    suspend fun fetchImagesList(@Path("breed")breed: String):
            Response<WrapperImages>

}