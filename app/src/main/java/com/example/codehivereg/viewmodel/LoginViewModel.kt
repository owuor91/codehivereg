package com.example.codehivereg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codehivereg.models.LoginRequest
import com.example.codehivereg.models.LoginResponse
import com.example.codehivereg.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var userRepository: UserRepository): ViewModel() {
  var loginLiveData = MutableLiveData<LoginResponse>()
  var errorLiveData = MutableLiveData<String>()
  
  fun loginStudent(loginRequest: LoginRequest){
    viewModelScope.launch {
      var response = userRepository.loginStudent(loginRequest)
      if (response.isSuccessful){
        loginLiveData.postValue(response.body())
      }
      else{
        errorLiveData.postValue(response.errorBody()?.string())
      }
    }
  }
}