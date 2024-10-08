package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.OnBackPressedCallback



class Dashboard : AppCompatActivity() {

    private lateinit var Logout:Button
    private lateinit var active:Button
    private lateinit var add:Button
    private lateinit var histry:Button

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        Logout=findViewById(R.id.logout)
        active=findViewById(R.id.activekey)
        add=findViewById(R.id.addkey)
        histry=findViewById(R.id.history)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashoard)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Logout.setOnClickListener {

            val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Close the dashboard activity
        }

        active.setOnClickListener {
            val intent = Intent(this, ActiveKeyDashboard::class.java)
            startActivity(intent)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                counter++
                if (counter == 2) {
                    finishAffinity()  // This will close the app or activity
                }
            }
        })

        add.setOnClickListener {
            val intent = Intent(this, AddKeyDashboard::class.java)
            startActivity(intent)
        }

        histry.setOnClickListener {
            val intent = Intent(this,HistoryDashboard::class.java)
            startActivity(intent)
        }


    }
}