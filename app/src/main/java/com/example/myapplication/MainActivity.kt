package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if the user is already logged in
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // If the user is logged in, navigate to the Dashboard directly
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
            finish()  // Close the login activity so the user can't go back to it
        }

        // Set up the click listener for the login button
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            // Check login credentials
            if (username == "admin" && password == "admin") {

                // Correct credentials, save login state in SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                // Correct credentials, navigate to Dashboard
                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
                finish()  // Close the login activity

            } else {
                // Incorrect credentials, show error message
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
