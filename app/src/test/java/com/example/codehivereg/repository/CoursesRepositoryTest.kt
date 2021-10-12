package com.example.codehivereg.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codehivereg.CoroutinesRule
import com.example.codehivereg.models.Course
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CoursesRepositoryTest {
  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()
  
  @get:Rule
  val coroutinesRule = CoroutinesRule()
  
  @Before
  fun setup() {
    MockKAnnotations.init(this)
  }
  
  @MockK
  lateinit var coursesRepository: CoursesRepository
  
  @Test
  fun testgetCourses() {
    
    val courseList: List<Course> = ArrayList<Course>()
    coEvery { coursesRepository.fetchCourses("1234") } returns Response.success(courseList)
    
    GlobalScope.launch(Dispatchers.IO) {
      Assert.assertEquals(coursesRepository.fetchCourses("1234"), courseList)
    }
  }
}