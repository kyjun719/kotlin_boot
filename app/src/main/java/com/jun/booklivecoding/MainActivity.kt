package com.jun.booklivecoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jun.booklivecoding.joyce.ui.StartActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.joyce_app_btn).setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
        }
    }
}