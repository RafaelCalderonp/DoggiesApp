package com.example.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.model.local.entities.DoggiesImage

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllImagesList(listBreed: List<DoggiesImage>)

    @Update
    suspend fun updateFavImages(doggiesImage: DoggiesImage)

    @Query("SELECT * FROM images_table WHERE breed = :breed")
    fun getAllDoggiesImages(breed : String): LiveData<List<DoggiesImage>>

    // Funcion que trae todos los favoritos
    @Query("SELECT * FROM images_table WHERE fav = 1")
    fun getAllFavImages(): LiveData<List<DoggiesImage>>


}