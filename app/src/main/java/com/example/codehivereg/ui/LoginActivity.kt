package com.example.codehivereg.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.codehivereg.Constants
import com.example.codehivereg.R
import com.example.codehivereg.databinding.ActivityLoginBinding
import com.example.codehivereg.models.LoginRequest
import com.example.codehivereg.models.LoginResponse
import com.example.codehivereg.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
  lateinit var binding: ActivityLoginBinding
  val loginViewModel: LoginViewModel by viewModels()
  lateinit var sharedPrefs: SharedPreferences
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)
    sharedPrefs = getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
  }
  
  override fun onResume() {
    super.onResume()
    binding.btnLogin.setOnClickListener {
      validateLogin()
      binding.tvError.visibility = View.GONE
    }
    
    loginViewModel.loginLiveData.observe(this, {loginResponse->
      var editor = sharedPrefs.edit()
      editor.putString(Constants.ACCESS_TOKEN, "Bearer ${loginResponse.accessToken}")
      editor.putString(Constants.STUDENT_ID, loginResponse.studentId)
      editor.apply()
      
      binding.progressBar.visibility = View.GONE
      Toast.makeText(baseContext, loginResponse.message, Toast.LENGTH_LONG).show()
      startActivity(Intent(baseContext, CoursesActivity::class.java))
    })
    
    loginViewModel.errorLiveData.observe(this, {error->
      binding.progressBar.visibility = View.GONE
      binding.tvError.visibility = View.VISIBLE
      binding.tvError.text = error
    })
  }
  
  fun validateLogin(){
    var email = binding.etEmail.text.toString()
    var password = binding.etPassword.text.toString()
    var error = false
    
    if (email.isEmpty() || email.isBlank()){
      error=true
      binding.tilEmail.error = "Email is required"
    }
  
    if (password.isEmpty() || password.isBlank()){
      error=true
      binding.tilPassword.error = "Password is required"
    }
    
    if (!error){
      binding.progressBar.visibility = View.VISIBLE
      var loginRequest = LoginRequest(email, password)
      loginViewModel.loginStudent(loginRequest)
    }
  }
}