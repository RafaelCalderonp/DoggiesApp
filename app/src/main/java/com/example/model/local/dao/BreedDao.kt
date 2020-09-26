package com.example.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.local.entities.Breed

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreedList(listBreed: List<Breed>)

    @Query("SELECT * FROM breed_table")
    fun getAllBreedList(): LiveData<List<Breed>>

}