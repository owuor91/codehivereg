package com.example.codehivereg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoursesAdapter(var courseList: List<Course>): RecyclerView.Adapter<CoursesViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
    var itemView = LayoutInflater.from(parent.context)
        .inflate(R.layout.course_list_item, parent, false)
    return CoursesViewHolder(itemView)
  }
  
  override fun onBindViewHolder(coursesViewHolder: CoursesViewHolder, position: Int) {
    var currentCourse = courseList.get(position)
    coursesViewHolder.tvCourseName.text = currentCourse.courseName
    coursesViewHolder.tvCourseCode.text = currentCourse.courseCode
    coursesViewHolder.tvDescription.text = currentCourse.description
    coursesViewHolder.tvInstructor.text = currentCourse.instructor
  }
  
  override fun getItemCount(): Int {
    return courseList.size
  }
}

class CoursesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
  var tvCourseName = itemView.findViewById<TextView>(R.id.tvCourseName)
  var tvCourseCode = itemView.findViewById<TextView>(R.id.tvCourseCode)
  var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
  var tvInstructor = itemView.findViewById<TextView>(R.id.tvInstructor)
}