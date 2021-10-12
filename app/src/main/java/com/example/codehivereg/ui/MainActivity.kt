package com.example.codehivereg.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.codehivereg.Constants
import com.example.codehivereg.databinding.ActivityMainBinding
import com.example.codehivereg.models.RegistrationRequest
import com.example.codehivereg.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  lateinit var binding: ActivityMainBinding
  val userViewModel: UserViewModel by viewModels()
  lateinit var sharedPrefs: SharedPreferences
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    sharedPrefs = getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)
    setupSpinner()
    clickRegister()
    redirectUser()
  }
  
  fun redirectUser(){
    var token = sharedPrefs.getString(Constants.ACCESS_TOKEN, Constants.EMPTY_STRING)
    if (token!!.isNotEmpty()){
      startActivity(Intent(baseContext, CoursesActivity::class.java))
    }
    else{
      startActivity(Intent(baseContext, LoginActivity::class.java))
    }
  }
  
  fun setupSpinner() {
    var nationalities = arrayOf("Kenyan", "Rwandan", "South Sudanese", "Sudanese", "Ugandan")
    var nationalitiesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nationalities)
    nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    binding.spNationality.adapter = nationalitiesAdapter
  }
  
  fun clickRegister() {
    var error = false
    binding.btnRegister.setOnClickListener {
      binding.pbRegistration.visibility = View.VISIBLE
      var name = binding.etName.text.toString()
      if (name.isEmpty()) {
        error = true
        binding.etName.setError("Name is required")
      }
      var dob = binding.etDob.text.toString()
      var nationality = binding.spNationality.selectedItem.toString().uppercase()
      var password = binding.etPassword.text.toString()
      var phone = binding.etPhone.text.toString()
      var email = binding.etEmail.text.toString()
      if (email.isEmpty()) {
        error = true
        binding.etEmail.setError("Name is required")
      }
      
      var regRequest = RegistrationRequest(
        name = name, phoneNumber = phone, email = email, dateOfBirth = dob,
        nationality = nationality, password = password
      )
      
      userViewModel.registerStudent(regRequest)
      
    }
    
  }
  
  override fun onResume() {
    super.onResume()
    userViewModel.regResponseLiveData.observe(this, {regResponse->
      binding.pbRegistration.visibility = View.GONE
      if (!regResponse.studentId.isNullOrEmpty()){
        Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
      }
    })
    
    userViewModel.regErrorLiveData.observe(this, {error->
      binding.pbRegistration.visibility = View.GONE
      Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
    })
    
    binding.tvLogin.setOnClickListener {
      startActivity(Intent(baseContext, LoginActivity::class.java))
    }
  }
}