package com.example.codehivereg.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codehivereg.models.Course

@Dao
interface CoursesDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCourse(course: Course)
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCourses(vararg courses: Course)
  
  @Query("SELECT * FROM courses")
  fun getCourses(): LiveData<List<Course>>
}