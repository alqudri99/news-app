package com.alqudri.newsapp.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alqudri.newsapp.data.dao.StudentsDao
import com.alqudri.newsapp.data.entities.Students

@Database(entities = arrayOf(Students::class), version = 1)
abstract class StudentDatabase: RoomDatabase(){
    abstract fun studentDao(): StudentsDao
    companion object{
        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase?{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, StudentDatabase::class.java, "student.db" ).build()
            }
            return INSTANCE
        }

        fun destroy(){
            INSTANCE = null
        }
    }
}