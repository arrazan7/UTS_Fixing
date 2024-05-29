package com.example.uts_fixing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uts_fixing.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val savedImage = intent.getIntExtra(PaymentActivity.EXTRA_GAMBAR, 1)
        val savedTitle = intent.getStringExtra(PaymentActivity.EXTRA_MOVIE)
        val savedBioskop = intent.getStringExtra(PaymentActivity.EXTRA_BIOSKOP)
        val savedSeat = intent.getStringExtra(PaymentActivity.EXTRA_SEAT)
        val savedNumberSeat = intent.getStringExtra(PaymentActivity.EXTRA_NUMBER_SEAT)
        val savedMetode = intent.getStringExtra(PaymentActivity.EXTRA_METODE)
        val savedAkun = intent.getStringExtra(PaymentActivity.EXTRA_ACCOUNT)
        val savedBayar = intent.getStringExtra(PaymentActivity.EXTRA_BAYAR)
        val savedHargaKursi = intent.getStringExtra(PaymentActivity.EXTRA_HARGA_KURSI)
        val savedHargaTotal = intent.getStringExtra(PaymentActivity.EXTRA_HARGA_TOTAL)
        val savedDate = intent.getStringExtra(PaymentActivity.EXTRA_DATE)
        val savedWaktu = intent.getStringExtra(PaymentActivity.EXTRA_WAKTU)

        with(binding){
            movieImage.setImageResource(savedImage)
            movieTitle.text = savedTitle
            movieBioskop.text = savedBioskop
            movieDate.text = savedDate
            movieTime.text = savedWaktu
            seat.text = savedSeat
            numberSeat.text = savedNumberSeat
            payMethod.text = "$savedMetode ($savedBayar)"
            txtHargaSeat.text = "$savedSeat seat"
            hargaSeat.text = savedHargaKursi
            actualPay.text = savedHargaTotal

            if (savedAkun == "") {
                accountNumber.text = "(bayar tunai)"
            }
            else {
                accountNumber.text = savedAkun
            }
        }
    }
}