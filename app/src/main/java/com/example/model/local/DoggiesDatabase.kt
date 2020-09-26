package com.example.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.model.local.dao.BreedDao
import com.example.model.local.dao.ImagesDao
import com.example.model.local.entities.Breed
import com.example.model.local.entities.DoggiesImage

@Database(entities = [Breed::class, DoggiesImage::class], version = 1, exportSchema = false)
abstract class BreedRoomDatabase : RoomDatabase() {

    abstract fun breedDao(): BreedDao
    abstract fun imagesDao(): ImagesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BreedRoomDatabase? = null

        fun getDatabase(context: Context): BreedRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BreedRoomDatabase::class.java,
                    "doggies_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
