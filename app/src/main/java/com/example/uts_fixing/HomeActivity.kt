package com.example.uts_fixing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts_fixing.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            username.setText(LoginActivity.EXTRA_USERNAME)

            btnMovie1.setOnClickListener {
                val intentToDetailActivity =
                    Intent(this@HomeActivity, DetailActivity::class.java)
                startActivity(intentToDetailActivity)
            }

            btnMovie2.setOnClickListener {
                val intentToDetailActivity2 =
                    Intent(this@HomeActivity, DetailActivity2::class.java)
                startActivity(intentToDetailActivity2)
            }

            btnMovie3.setOnClickListener {
                val intentToDetailActivity3 =
                    Intent(this@HomeActivity, DetailActivity3::class.java)
                startActivity(intentToDetailActivity3)
            }

            btnMovie4.setOnClickListener {
                val intentToDetailActivity4 =
                    Intent(this@HomeActivity, DetailActivity4::class.java)
                startActivity(intentToDetailActivity4)
            }

            btnMovie5.setOnClickListener {
                val intentToDetailActivity5 =
                    Intent(this@HomeActivity, DetailActivity5::class.java)
                startActivity(intentToDetailActivity5)
            }

            btnMovie6.setOnClickListener {
                val intentToDetailActivity6 =
                    Intent(this@HomeActivity, DetailActivity6::class.java)
                startActivity(intentToDetailActivity6)
            }

            btnMovie7.setOnClickListener {
                val intentToDetailActivity7 =
                    Intent(this@HomeActivity, DetailActivity7::class.java)
                startActivity(intentToDetailActivity7)
            }
        }
    }
}