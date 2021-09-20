package com.example.codehivereg.api

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.codehivereg.BuildConfig
import com.example.codehivereg.CodeHiveRegApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
  
  val client = OkHttpClient.Builder()
    .addInterceptor(ChuckerInterceptor(CodeHiveRegApplication.appContext))
    .build()
  
  var retrofit = Retrofit.Builder()
    .baseUrl("http://13.244.243.129")
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()
  fun <T> buildApiClient(apiInterface: Class<T>): T {
    return retrofit.create(apiInterface)
  }
}

fun getLoggingInterceptor(): HttpLoggingInterceptor {
  var httpLoggingInterceptor = HttpLoggingInterceptor()
  
  if (BuildConfig.DEBUG) {
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
  }
  return httpLoggingInterceptor
}
