package com.example.codehivereg.models

import android.util.Log
import java.io.File

interface Printer {
  fun printText(text: String)
  
  fun printImage(image: File)
}

class InkjetPrinter: Printer{
  override fun printText(text: String) {
    print("ihcehrifgwrf")
  }
  
  override fun printImage(image: File) {
    print("010101019193333833")
  }
}

class LaserJetPrinter: Printer{
  override fun printText(text: String) {
    Log.d("ANDROID123","ihcehrifgwrf")
  }
  
  override fun printImage(image: File) {
    print("010101019193333833")
  }
}