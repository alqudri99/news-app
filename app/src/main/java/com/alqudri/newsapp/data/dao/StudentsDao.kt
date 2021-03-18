package com.alqudri.newsapp.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.alqudri.newsapp.data.entities.Students

@Dao
interface StudentsDao {
    @Query("SELECT * FROM students")
    fun getAll(): List<Students>

    @Insert(onConflict = REPLACE)
    fun insert(students: Students)

    @Delete
    fun delete(students: Students)

    @Query("UPDATE students SET name = :name, nim = :nim, gender = :gender WHERE id = :id")
    fun update(id: Int, name: String, nim: String, gender: String)
}