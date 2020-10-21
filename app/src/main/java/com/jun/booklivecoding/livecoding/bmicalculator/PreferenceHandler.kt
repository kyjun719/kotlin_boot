package com.jun.booklivecoding.livecoding.bmicalculator

import android.content.Context
import android.content.SharedPreferences

object PreferenceHandler {
    lateinit var preference: SharedPreferences

    fun init(context: Context) {
        preference = context.getSharedPreferences("bmi", Context.MODE_PRIVATE)
    }

    fun saveData(height: Int, weight: Int) {
        val editor = preference.edit()
        editor.putInt("height",height)
        editor.putInt("weight",weight)
        editor.apply()
    }

    fun getData(): BmiDTO {
        val height = preference.getInt("height",0)
        val weight = preference.getInt("weight",0)
        return BmiDTO(height, weight)
    }
}