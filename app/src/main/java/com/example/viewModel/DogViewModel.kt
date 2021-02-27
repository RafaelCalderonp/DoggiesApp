package com.example.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.model.DoggiesRepository
import com.example.model.local.DoggiesDatabase
import com.example.model.local.entities.Breed
import com.example.model.local.entities.DoggiesImage
import kotlinx.coroutines.launch

class   DogViewModel(application: Application): AndroidViewModel(application) {

    private val repository : DoggiesRepository

    init {
        val db = DoggiesDatabase.getDatabase(application)
        val breedDao = db.breedDao()
        val imageDao = db.imagesDao()
        repository = DoggiesRepository(imageDao ,breedDao)

        viewModelScope.launch {
            repository.fetchBreed()
        }
    }

    //Todas las razas de perro desde la DataBase
    fun getBreedList(): LiveData<List<Breed>> = repository.breedListLivedata

    // Para las images
    private var breedSelected : String = ""

    fun getImagesByBreedFromInternet(breed: String) = viewModelScope.launch {
        breedSelected = breed
        repository.fetchDogImages(breed)
    }

    fun getImages(): LiveData<List<DoggiesImage>> = repository.getAllImagesByBreed(breedSelected)


    fun updateFav(doggiesImage: DoggiesImage) = viewModelScope.launch {
        repository.updateFavImages(doggiesImage)
    }

}