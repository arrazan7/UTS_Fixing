package com.example.uts_fixing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.uts_fixing.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val savedPassword = "rahasia"

    companion object{
        var EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(4000)
        installSplashScreen()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            btnToHomePage.setOnClickListener {
                val loginUser = loginUsername.text.toString()
                val loginPass = loginPassword.text.toString()
                EXTRA_USERNAME = loginUser

                val intentToHomeActivity =
                    Intent(this@LoginActivity, HomeActivity::class.java)
                intentToHomeActivity.putExtra(EXTRA_USERNAME,loginUser)

                if (loginPass == savedPassword) {
                    startActivity(intentToHomeActivity)
                } else {
                    // Login salah, tampilkan pesan kesalahan
                    Toast.makeText(this@LoginActivity, "Login Salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}