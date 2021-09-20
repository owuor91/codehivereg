package com.example.codehivereg.api

import com.example.codehivereg.models.Course
import com.example.codehivereg.models.LoginRequest
import com.example.codehivereg.models.LoginResponse
import com.example.codehivereg.models.RegistrationRequest
import com.example.codehivereg.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
  @POST("/students/register")
  suspend fun registerStudent(@Body registrationRequest: RegistrationRequest): Response<RegistrationResponse>
  
  @POST("/students/login")
  suspend fun loginStudent(@Body loginRequest: LoginRequest): Response<LoginResponse>
  
  @GET("/courses")
  suspend fun fetchCourses(@Header("Authorization") token: String): Response<List<Course>>
}