package com.example.codehivereg.repository

import androidx.lifecycle.LiveData
import com.example.codehivereg.api.ApiInterface
import com.example.codehivereg.database.CoursesDao
import com.example.codehivereg.models.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CoursesRepository @Inject constructor(val service: ApiInterface, val coursesDao: CoursesDao) {
  
  suspend fun fetchCourses(accessToken: String): Response<List<Course>>
  = withContext(Dispatchers.IO){
    var response = service.fetchCourses(accessToken)
    response.body()?.forEach { course ->
      coursesDao.insertCourse(course)
    }
    return@withContext response
  }
  
  fun getCoursesFromDb(): LiveData<List<Course>>{
    return coursesDao.getCourses()
  }
}