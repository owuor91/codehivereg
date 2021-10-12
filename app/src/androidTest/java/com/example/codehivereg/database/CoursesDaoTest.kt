package com.example.codehivereg.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.codehivereg.getOrAwaitValue
import com.example.codehivereg.models.Course
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CoursesDaoTest {
  private lateinit var database: CodehiveDatabase
  lateinit var coursesDao: CoursesDao
  
  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()
  
  @Before()
  fun setup(){
    database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), CodehiveDatabase::class
      .java).allowMainThreadQueries().build()
    
    coursesDao = database.getCoursesDao()
  }
  
  @After()
  fun teardown(){
    database.close()
  }
  
  @Test
  fun testInsertCourse() {
    runBlockingTest{
      var course = Course("1763156", "Intro to geology", "GEO123", "Geology Intro", "Juma JZ")
      coursesDao.insertCourse(course)
      val courses = coursesDao.getCourses().getOrAwaitValue()
      assertThat(courses).contains(course)
    }
  }
  
  @Test
  fun testListCourses(){
    runBlockingTest {
      var course = Course("1763156", "Intro to geology", "GEO123", "Geology Intro", "Juma JZ")
      var course2 = Course("123566", "Intro to biology", "BIO123", "Geology Intro", "Juma JZ")
      var course3 = Course("643422", "Intro to chemistry", "CHE123", "Geology Intro", "Juma JZ")
      coursesDao.insertCourse(course)
      coursesDao.insertCourse(course2)
      coursesDao.insertCourse(course3)
      val courses = coursesDao.getCourses().getOrAwaitValue()
      assertThat(courses.size).isEqualTo(3)
    }
  }
}