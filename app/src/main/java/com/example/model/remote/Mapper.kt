package com.example.model.remote

import com.example.model.local.entities.Breed
import com.example.model.local.entities.DoggiesImage
import com.example.model.remote.pojo.WrapperBreed
import com.example.model.remote.pojo.WrapperImages

fun fromInternetToBreedEntity(wrapper: WrapperBreed): List<Breed> {
    return wrapper.message.map {
        Breed(breed = it) }
}

fun fromInternetToImagesEntity(wrapper: WrapperImages, breed: String): List<DoggiesImage> {
    return wrapper.message.map {
        DoggiesImage(imageUrl = it, breed = breed) }
}