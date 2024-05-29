package com.example.uts_fixing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts_fixing.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_COUNT = "extra_count"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val yourName = intent.getStringExtra(LoginActivity.EXTRA_USERNAME)

        with(binding){
            getTicket.setOnClickListener {
                val intentToPaymentActivity =
                    Intent(this@DetailActivity, PaymentActivity::class.java)
                intentToPaymentActivity.putExtra(EXTRA_TITLE, "Oppenheimer")
                intentToPaymentActivity.putExtra(EXTRA_COUNT, "1")
                intentToPaymentActivity.putExtra(EXTRA_IMAGE, R.drawable.poster_12)
                startActivity(intentToPaymentActivity)
            }
            btnBack.setOnClickListener {
                val intentToHomeActivity =
                    Intent(this@DetailActivity, HomeActivity::class.java)
                startActivity(intentToHomeActivity)
            }
        }
    }
}