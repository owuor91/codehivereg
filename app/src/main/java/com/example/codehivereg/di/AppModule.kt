package com.example.codehivereg.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.codehivereg.CodeHiveRegApplication
import com.example.codehivereg.api.ApiInterface
import com.example.codehivereg.database.CodehiveDatabase
import com.example.codehivereg.database.CoursesDao
import com.example.codehivereg.models.LaserJetPrinter
import com.example.codehivereg.models.Printer
import com.example.codehivereg.repository.CoursesRepository
import com.example.codehivereg.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  
  @Singleton
  @Provides
  fun provideRetrofit(client: OkHttpClient): Retrofit{
    var retrofit = Retrofit.Builder()
      .baseUrl("http://13.244.243.129")
      .addConverterFactory(GsonConverterFactory.create())
      .client(client)
      .build()
    return retrofit
  }
  
  @Provides
  fun provideOkHttpClient(): OkHttpClient{
    val client = OkHttpClient.Builder()
      .addInterceptor(ChuckerInterceptor(CodeHiveRegApplication.appContext))
      .build()
    return client
  }
  
  @Provides
  fun provideService(retrofit: Retrofit): ApiInterface{
    return retrofit.create(ApiInterface::class.java)
  }
  
  @Singleton
  @Provides
  fun provideDatabase(@ApplicationContext context: Context): CodehiveDatabase{
    return CodehiveDatabase.getDatabase(context)
  }
  
  @Singleton
  @Provides
  fun provideCoursesDao(database: CodehiveDatabase): CoursesDao{
    return database.getCoursesDao()
  }
  
  @Singleton
  @Provides
  fun provideCoursesRepository(service: ApiInterface, coursesDao: CoursesDao): CoursesRepository{
    return CoursesRepository(service, coursesDao)
  }
  
  @Singleton
  @Provides
  fun provideUserRepository(service: ApiInterface): UserRepository{
    return UserRepository(service)
  }
  
  @Provides
  fun provideLaserJetPrinter(): Printer{
    return LaserJetPrinter()
  }
}