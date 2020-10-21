package com.jun.booklivecoding.livecoding.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jun.booklivecoding.R
import kotlinx.android.synthetic.main.activity_bmi.*

class BmiActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        init()
        navController = container_fragment.findNavController()
    }

    private fun init() {
        PreferenceHandler.init(this)
        actionBar?.title="BMI Calculator"
    }
}