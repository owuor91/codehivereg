package com.example.codehivereg.api

import com.example.codehivereg.models.RegistrationRequest
import com.example.codehivereg.models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
  @POST("/students/register")
  fun registerStudent(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>
}