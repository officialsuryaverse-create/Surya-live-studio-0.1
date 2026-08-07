package com.surya.livestudio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGoLive = findViewById<Button>(R.id.btnGoLive)

        btnGoLive.setOnClickListener {
            startActivity(Intent(this, GoLiveActivity::class.java))
        }
    }
}
