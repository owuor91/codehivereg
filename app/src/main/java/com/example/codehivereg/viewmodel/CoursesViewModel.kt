package com.example.codehivereg.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codehivereg.models.Course
import com.example.codehivereg.repository.CoursesRepository
import kotlinx.coroutines.launch

class CoursesViewModel: ViewModel() {
  var coursesRepository = CoursesRepository()
  lateinit var coursesLiveData: LiveData<List<Course>>
  var errorLiveData = MutableLiveData<String>()
  
  fun fetchCourses(accessToken: String){
    viewModelScope.launch {
      coursesRepository.fetchCourses(accessToken)
    }
  }
  
  fun getDbCourses(){
    coursesLiveData = coursesRepository.getCoursesFromDb()
  }
  
}