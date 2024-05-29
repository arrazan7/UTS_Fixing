package com.example.uts_fixing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts_fixing.databinding.ActivityDetail5Binding

class DetailActivity5 : AppCompatActivity() {
    private lateinit var binding: ActivityDetail5Binding

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_COUNT = "extra_count"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            getTicket.setOnClickListener {
                val intentToPaymentActivity =
                    Intent(this@DetailActivity5, PaymentActivity::class.java)
                intentToPaymentActivity.putExtra(EXTRA_TITLE, "The Nun II")
                intentToPaymentActivity.putExtra(EXTRA_COUNT, "1")
                intentToPaymentActivity.putExtra(EXTRA_IMAGE, R.drawable.poster_52)
                startActivity(intentToPaymentActivity)
            }
            btnBack.setOnClickListener {
                val intentToHomeActivity =
                    Intent(this@DetailActivity5, HomeActivity::class.java)
                startActivity(intentToHomeActivity)
            }
        }
    }
}