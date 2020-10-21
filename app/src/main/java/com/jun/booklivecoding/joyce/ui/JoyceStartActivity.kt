package com.jun.booklivecoding.joyce.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jun.booklivecoding.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_start.*

class JoyceStartActivity: AppCompatActivity() {
    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        navController = nav_host_fragment.findNavController()
    }
}