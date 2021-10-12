package com.example.codehivereg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codehivereg.models.RegistrationRequest
import com.example.codehivereg.models.RegistrationResponse
import com.example.codehivereg.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val userRepository: UserRepository): ViewModel() {
  
  var regResponseLiveData = MutableLiveData<RegistrationResponse>()
  var regErrorLiveData = MutableLiveData<String>()
  
  fun registerStudent(registrationRequest: RegistrationRequest){
    viewModelScope.launch {
      var response = userRepository.registerUser(registrationRequest)
      if (response.isSuccessful){
        regResponseLiveData.postValue(response.body())
      }
      else{
        regErrorLiveData.postValue(response.errorBody()?.string())
      }
    }
  }
}