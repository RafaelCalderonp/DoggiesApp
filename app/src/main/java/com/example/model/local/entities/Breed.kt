package com.example.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_table")
data class Breed(@PrimaryKey val breed: String)