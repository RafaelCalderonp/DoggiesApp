package com.example.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_table")
data class DoggiesImage(@PrimaryKey val imageUrl: String,
                        val breed: String,
                        var fav: Boolean = false)