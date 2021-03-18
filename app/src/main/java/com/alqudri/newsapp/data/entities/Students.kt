package com.alqudri.newsapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Students(
    val name: String,
    val gender: String,
    val nim: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)