package com.example.codehivereg.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codehivereg.Constants
import com.example.codehivereg.CoursesAdapter
import com.example.codehivereg.R.id
import com.example.codehivereg.R.layout
import com.example.codehivereg.models.Course
import com.example.codehivereg.viewmodel.CoursesViewModel

class CoursesActivity : AppCompatActivity() {
  lateinit var sharedPrefs: SharedPreferences
  lateinit var rvCourses: RecyclerView
  val coursesViewModel: CoursesViewModel by viewModels()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_courses)
    sharedPrefs = getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
  }
  
  override fun onResume() {
    super.onResume()
    
    coursesViewModel.getDbCourses()
    coursesViewModel.coursesLiveData.observe(this, {courses->
      if (courses.isEmpty()){
        val accessToken = sharedPrefs.getString(Constants.ACCESS_TOKEN, Constants.EMPTY_STRING)
        if (accessToken!!.isNotEmpty()){
          coursesViewModel.fetchCourses(accessToken)
        }
      }else{
        displayCourses(courses)
      }
    })
    
    coursesViewModel.errorLiveData.observe(this, {error->
      Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
    })
  }
  
  fun displayCourses(coursesList: List<Course>){
    rvCourses = findViewById(id.rvCourses)
    var coursesAdapter = CoursesAdapter(coursesList)
    rvCourses.apply {
      layoutManager= LinearLayoutManager(baseContext)
      adapter = coursesAdapter
    }
  }
}