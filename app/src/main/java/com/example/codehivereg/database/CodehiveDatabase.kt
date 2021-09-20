package com.example.codehivereg.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.codehivereg.models.Course

@Database(entities = arrayOf(Course::class), version = 1)
abstract class CodehiveDatabase: RoomDatabase() {
  abstract fun getCoursesDao(): CoursesDao
  
  companion object{
    private var database: CodehiveDatabase? = null
    
    fun getDatabase(context: Context): CodehiveDatabase{
      if (database==null){
        database = Room.databaseBuilder(context, CodehiveDatabase::class.java, "codehive-db")
          .fallbackToDestructiveMigration()
          .build()
      }
      return database as CodehiveDatabase
    }
  }
}