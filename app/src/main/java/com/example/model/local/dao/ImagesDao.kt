package com.example.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.local.entities.Breed
import com.example.model.local.entities.DoggiesImage

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImagesList(listBreed: List<DoggiesImage>)

    @Query("SELECT * FROM images_table")
    fun getAllDoggiesImages(): LiveData<List<DoggiesImage>>

}