package com.example.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.model.local.dao.BreedDao
import com.example.model.local.dao.ImagesDao
import com.example.model.local.entities.DoggiesImage
import com.example.model.remote.RetrofitInstance
import com.example.model.remote.fromInternetToBreedEntity
import com.example.model.remote.fromInternetToImagesEntity

class DoggiesRepository(private val imagesDao: ImagesDao, private val breedDao: BreedDao) {

    private val networkService = RetrofitInstance.retrofitInstance()
    val breedListLivedata = breedDao.getAllBreedList() //devuelve listado de perros.

    // Solicita el listado de perros al servicio y los guarda en la base de datos.
    suspend fun fetchBreed() {
        val service = kotlin.runCatching { networkService.fetchBreedList() }
        service.onSuccess {
            when (it.code()) {
                200 -> it.body()?.let {
                    breedDao.insertAllBreedList(fromInternetToBreedEntity(it))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    //Recibe la raza y realiza la solicitud guardando el elemento en la Base de datos.
    suspend fun fetchDogImages(breed: String) {
        val service = kotlin.runCatching { networkService.fetchImagesList(breed) }
        service.onSuccess {
            when (it.code()) {
                200 -> it.body()?.let {
                    imagesDao.insertAllImagesList(fromInternetToImagesEntity(it, breed))
                }
                else -> Log.d("REPO-IMG", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    // Retorna las imagenes por raza desde la base de datos.
    fun getAllImagesByBreed(breed: String): LiveData<List<DoggiesImage>> {
        return imagesDao.getAllDoggiesImages(breed)
    }

    suspend fun updateFavImages(doggiesImage: DoggiesImage) {
        imagesDao.updateFavImages(doggiesImage)
    }

}

