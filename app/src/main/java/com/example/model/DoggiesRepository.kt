package com.example.model

import android.content.Context
import android.util.Log
import com.example.model.local.dao.BreedDao
import com.example.model.local.dao.ImagesDao
import com.example.model.remote.RetrofitInstance
import com.example.model.remote.pojo.WrapperBreed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoggiesRepository(imagesDao: ImagesDao, breedDao: BreedDao) {

    private val networkService = RetrofitInstance.retrofitInstance()
    val breedListLivedata = breedDao.getAllBreedList()
    val imageListLiveData = imagesDao.getAllDoggiesImages()

    fun fetchDataBreedFromServer() {
        val call = networkService.fetchBreedList()
        call.enqueue(object : Callback<WrapperBreed> {
            override fun onResponse(call: Call<WrapperBreed>, response: Response<WrapperBreed>) {
                response.body()
            }

            override fun onFailure(call: Call<WrapperBreed>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    // Ejemplo de manejo con retrofit en el repositorio
    fun retrowithCoroutines()= CoroutineScope(Dispatchers.IO).launch{
        val service = kotlin.runCatching{ networkService.fetchBreedListCorutinas()}
        service.onSuccess {

        }
        service.onFailure {

        }
    }

    // Ejemplo de metodo suspended para manejarlo en el viewModel
    suspend fun retrowithCoroutines2() {
        networkService.fetchBreedListCorutinas()
    }



}

