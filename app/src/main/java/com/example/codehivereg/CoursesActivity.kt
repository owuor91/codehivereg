package com.example.codehivereg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoursesActivity : AppCompatActivity() {
  lateinit var rvCourses: RecyclerView
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_courses)
    displayCourses()
  }
  
  fun displayCourses(){
    var courseList = listOf(
        Course("IOT", "IO207", "IOT for 21st century connectivity", "Barre Yassin"),
        Course("Mobile Development", "AND101", "Android development with Kotlin", "John Owuor"),
        Course("Python", "PY101", "Backend development with Python", "James Mwai"),
        Course("Javascript", "JS101", "JS for fun and for profit", "Purity Maina"),
        Course("UX Research", "UX101", "Research for Humans", "Joy Wambui")
    )
    
    rvCourses = findViewById(R.id.rvCourses)
    var coursesAdapter = CoursesAdapter(courseList)
    rvCourses.apply {
      layoutManager= LinearLayoutManager(baseContext)
      adapter = coursesAdapter
    }
  }
}