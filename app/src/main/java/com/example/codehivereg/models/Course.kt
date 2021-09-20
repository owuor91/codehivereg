package com.example.codehivereg.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Courses")
data class Course(
  @PrimaryKey @NonNull @SerializedName("course_id") var courseId: String,
  @SerializedName("course_name") var courseName: String,
  @SerializedName("course_code") var courseCode: String,
  var description: String,
  var instructor: String
)
