package com.example.codehivereg.repository

import com.example.codehivereg.api.ApiInterface
import com.example.codehivereg.models.LoginRequest
import com.example.codehivereg.models.LoginResponse
import com.example.codehivereg.models.RegistrationRequest
import com.example.codehivereg.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(val service: ApiInterface){
  
  suspend fun registerUser(registrationRequest: RegistrationRequest):Response<RegistrationResponse> =
    withContext(Dispatchers.IO){
      var response = service.registerStudent(registrationRequest)
      return@withContext response
    }
  
  suspend fun loginStudent(loginRequest: LoginRequest): Response<LoginResponse> =
    withContext(Dispatchers.IO){
      var response = service.loginStudent(loginRequest)
      return@withContext response
    }
}