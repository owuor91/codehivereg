package com.example.codehivereg.repository

import androidx.lifecycle.LiveData
import com.example.codehivereg.CodeHiveRegApplication
import com.example.codehivereg.api.ApiClient
import com.example.codehivereg.api.ApiInterface
import com.example.codehivereg.database.CodehiveDatabase
import com.example.codehivereg.models.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepository {
  var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
  val db = CodehiveDatabase.getDatabase(CodeHiveRegApplication.appContext)
  
  suspend fun fetchCourses(accessToken: String)
  = withContext(Dispatchers.IO){
    var response = retrofit.fetchCourses(accessToken)
    var dao = db.getCoursesDao()
    response.body()?.forEach { course ->
      dao.insertCourse(course)
    }
  }
  
  fun getCoursesFromDb(): LiveData<List<Course>>{
    return db.getCoursesDao().getCourses()
  }
}