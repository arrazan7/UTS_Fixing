package com.example.uts_fixing

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import com.example.uts_fixing.databinding.ActivityPaymentBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.properties.Delegates

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var kursi: IntArray
    private lateinit var memilihTanggal: TextView
    private lateinit var memilihWaktu: TextView
    private lateinit var bayar1: Array<String>
    private lateinit var bayar2: Array<String>
    private lateinit var bayar3: Array<String>
    private lateinit var bayar4: Array<String>
    private var savedImage by Delegates.notNull<Int>()
    private lateinit var savedTitle: String
    var hargaPerKursi = 0
    var totalHarga = 0

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_BIOSKOP = "extra_bioskop"
        const val EXTRA_SEAT = "extra_seat"
        const val EXTRA_NUMBER_SEAT = "extra_number_seat"
        const val EXTRA_METODE = "extra_metode"
        const val EXTRA_ACCOUNT = "extra_account"
        const val EXTRA_BAYAR = "extra_bayar"
        const val EXTRA_HARGA_KURSI = "extra_harga_kursi"
        const val EXTRA_HARGA_TOTAL = "extra_harga_total"
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_WAKTU = "extra_waktu"
        const val EXTRA_GAMBAR = "extra_gambar"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var movieCount1 = intent.getStringExtra(DetailActivity.EXTRA_COUNT)
        var movieCount2 = intent.getStringExtra(DetailActivity2.EXTRA_COUNT)
        var movieCount3 = intent.getStringExtra(DetailActivity3.EXTRA_COUNT)
        var movieCount4 = intent.getStringExtra(DetailActivity4.EXTRA_COUNT)
        var movieCount5 = intent.getStringExtra(DetailActivity5.EXTRA_COUNT)
        var movieCount6 = intent.getStringExtra(DetailActivity6.EXTRA_COUNT)
        var movieCount7 = intent.getStringExtra(DetailActivity7.EXTRA_COUNT)

        if (movieCount1 == "1") {
            savedTitle = intent.getStringExtra(DetailActivity.EXTRA_TITLE).toString()
            savedImage = intent.getIntExtra(DetailActivity.EXTRA_IMAGE, 1)
        }
        else if (movieCount2 == "1") {
            savedTitle = intent.getStringExtra(DetailActivity2.EXTRA_TITLE).toString()
            savedImage = intent.getIntExtra(DetailActivity2.EXTRA_IMAGE, 1)
        }
        else if (movieCount3 == "1") {
            savedTitle = intent.getStringExtra(DetailActivity3.EXTRA_TITLE).toString()
            savedImage = intent.getIntExtra(DetailActivity3.EXTRA_IMAGE, 1)
        }
        else if (movieCount4 == "1") {
            savedTitle = intent.getStringExtra(DetailActivity4.EXTRA_TITLE).toString()
            savedImage = intent.getIntExtra(DetailActivity4.EXTRA_IMAGE, 1)
        }
        else if (movieCount5 == "1") {
            savedTitle = intent.getStringExtra(DetailActivity5.EXTRA_TITLE).toString()
            savedImage = intent.getIntExtra(DetailActivity5.EXTRA_IMAGE, 1)
        }
        else if (movieCount6 == "1") {
            savedTitle = intent.getStringExtra(DetailActivity6.EXTRA_TITLE).toString()
            savedImage = intent.getIntExtra(DetailActivity6.EXTRA_IMAGE, 1)
        }
        else if (movieCount7 == "1") {
            savedTitle = intent.getStringExtra(DetailActivity7.EXTRA_TITLE).toString()
            savedImage = intent.getIntExtra(DetailActivity7.EXTRA_IMAGE, 1)
        }

        memilihTanggal = findViewById(R.id.txt_select_tanggal)
        memilihWaktu = findViewById(R.id.txt_select_waktu)
        bayar1 = resources.getStringArray(R.array.bank)
        bayar2 = resources.getStringArray(R.array.wallet)
        bayar3 = resources.getStringArray(R.array.kredit)
        bayar4 = resources.getStringArray(R.array.cash)
        kursi = resources.getIntArray(R.array.jumlah_seat)

        with(binding){
            val adapterBayar1 = ArrayAdapter(
                this@PaymentActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, bayar1
            )
            adapterBayar1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

            val adapterBayar2 = ArrayAdapter(
                this@PaymentActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, bayar2
            )
            adapterBayar2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

            val adapterBayar3 = ArrayAdapter(
                this@PaymentActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, bayar3
            )
            adapterBayar3.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

            val adapterBayar4 = ArrayAdapter(
                this@PaymentActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, bayar4
            )
            adapterBayar4.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)

            val adapterKursi = ArrayAdapter(
                this@PaymentActivity,
                android.R.layout.simple_spinner_item, // Layout item spinner
                kursi.toList() // Konversi array integer ke List<Integer>
            )
            adapterKursi.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            spinnerJumlahSeat.adapter = adapterKursi

            val myCalendar = Calendar.getInstance()

            val dateDialog = DatePickerDialog.OnDateSetListener{
                    view,year,month,dayOfMonth->
                myCalendar.set(Calendar.YEAR,year)
                myCalendar.set(Calendar.MONTH,month)
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateLable(myCalendar)
            }
            val timeDialog = TimePickerDialog(this@PaymentActivity, TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, minute)
                updateTimeLabel(myCalendar)
            }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), false)

            memilihTanggal.setOnClickListener{
                val datePickerDialog = DatePickerDialog(this@PaymentActivity, dateDialog, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
                datePickerDialog.show()
            }
            memilihWaktu.setOnClickListener {
                timeDialog.show()
            }

            spinnerJumlahSeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    var selectedJumlahSeat = 0
                    when(spinnerJumlahSeat.selectedItem) {
                        1 -> {
                            txtTotalSeat.text = "1"
                            selectedJumlahSeat = 1
                        }
                        2 -> {
                            txtTotalSeat.text = "2"
                            selectedJumlahSeat = 2
                        }
                        3 -> {
                            txtTotalSeat.text = "3"
                            selectedJumlahSeat = 3
                        }
                        4 -> {
                            txtTotalSeat.text = "4"
                            selectedJumlahSeat = 4
                        }
                        5 -> {
                            txtTotalSeat.text = "5"
                            selectedJumlahSeat = 5
                        }
                        6 -> {
                            txtTotalSeat.text = "6"
                            selectedJumlahSeat = 6
                        }
                        7 -> {
                            txtTotalSeat.text = "7"
                            selectedJumlahSeat = 7
                        }
                        8 -> {
                            txtTotalSeat.text = "8"
                            selectedJumlahSeat = 8
                        }
                        9 -> {
                            txtTotalSeat.text = "9"
                            selectedJumlahSeat = 9
                        }
                        10 -> {
                            txtTotalSeat.text = "10"
                            selectedJumlahSeat = 10
                        }
                    }

                    totalHarga = hargaPerKursi * selectedJumlahSeat
                    val reformatHargaTotal = formatCurrency(totalHarga)
                    txtHargaTotal.text = reformatHargaTotal
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Kosong
                }
            }

            spinnerJenisSeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    hargaPerKursi = when (spinnerJenisSeat.selectedItem.toString()) {
                        "Starium" -> 45000
                        "Regular" -> 50000
                        "RealD 3D" -> 170000
                        "4DX" -> 110000
                        "Sphere X" -> 130000
                        "Screen X" -> 70000
                        "Sweet Box" -> 130000
                        "Velvet Class" -> 180000
                        "Gold Class" -> 200000
                        "Satin Suite" -> 140000
                        else -> 0
                    }

                    val totalSeat = txtTotalSeat.text.toString()
                    if (totalSeat.isNotEmpty()) {
                        val angkaTotalSeat = totalSeat.toInt()
                        totalHarga = hargaPerKursi * angkaTotalSeat
                        val reformatHargaTotal = formatCurrency(totalHarga)
                        txtHargaSeat.text = formatCurrency(hargaPerKursi)
                        txtHargaTotal.text = reformatHargaTotal
                    }

                    when (spinnerJenisSeat.selectedItem.toString()) {
                        "Starium" -> {
                            txtPaymentSeat.text = "Starium"
                        }
                        "Regular" -> {
                            txtPaymentSeat.text = "Regular"
                        }
                        "RealD 3D" -> {
                            txtPaymentSeat.text = "RealD 3D"
                        }
                        "4DX" -> {
                            txtPaymentSeat.text = "4DX"
                        }
                        "Sphere X" -> {
                            txtPaymentSeat.text = "Sphere X"
                        }
                        "Screen X" -> {
                            txtPaymentSeat.text = "Screen X"
                        }
                        "Sweet Box" -> {
                            txtPaymentSeat.text = "Sweet Box"
                        }
                        "Velvet Class" -> {
                            txtPaymentSeat.text = "Velvet Class"
                        }
                        "Gold Class" -> {
                            txtPaymentSeat.text = "Gold Class"
                        }
                        "Satin Suite" -> {
                            txtPaymentSeat.text = "Satin Suite"
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Kosong
                }
            }

            // Tambahkan listener untuk spinner pertama
            spinnerMetodeBayar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    // Mendapatkan pilihan dari spinner pertama
                    val selectedItem = spinnerMetodeBayar.selectedItem.toString()

                    // Mengganti isi spinner kedua sesuai dengan pilihan pada spinner pertama
                    when (selectedItem) {
                        "Bank transfer" -> {
                            spinnerTujuanBayar.adapter = adapterBayar1
                            spinnerTujuanBayar.visibility = View.VISIBLE
                            accountNumber.visibility = View.VISIBLE
                        }
                        "E-Wallet transfer" -> {
                            spinnerTujuanBayar.adapter = adapterBayar2
                            spinnerTujuanBayar.visibility = View.VISIBLE
                            accountNumber.visibility = View.VISIBLE
                        }
                        "Kredit" -> {
                            spinnerTujuanBayar.adapter = adapterBayar3
                            spinnerTujuanBayar.visibility = View.VISIBLE
                            accountNumber.visibility = View.VISIBLE
                        }
                        "Cash" -> {
                            spinnerTujuanBayar.adapter = adapterBayar4
                            spinnerTujuanBayar.visibility = View.GONE
                            accountNumber.visibility = View.GONE
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Kosong
                }
            }

            btnOrderSummary.setOnClickListener {
                val tujuanBioskop = spinnerBioskop.selectedItem.toString()
                val tujuanSeat = spinnerJenisSeat.selectedItem.toString()
                val numberSeat = spinnerJumlahSeat.selectedItem.toString()
                val metodeBayar = spinnerMetodeBayar.selectedItem.toString()
                val tujuanBayar = spinnerTujuanBayar.selectedItem.toString()
                val hargaKursi = formatCurrency(hargaPerKursi)
                val hargaTotal = formatCurrency(totalHarga)
                val akunBayar = accountNumber.text.toString()

                val intentToResultActivity =
                    Intent(this@PaymentActivity, OrderActivity::class.java)
                intentToResultActivity.putExtra(EXTRA_MOVIE, savedTitle)
                intentToResultActivity.putExtra(EXTRA_BIOSKOP, tujuanBioskop)
                intentToResultActivity.putExtra(EXTRA_SEAT, tujuanSeat)
                intentToResultActivity.putExtra(EXTRA_NUMBER_SEAT, numberSeat)
                intentToResultActivity.putExtra(EXTRA_METODE, metodeBayar)
                intentToResultActivity.putExtra(EXTRA_ACCOUNT, akunBayar)
                intentToResultActivity.putExtra(EXTRA_BAYAR, tujuanBayar)
                intentToResultActivity.putExtra(EXTRA_HARGA_KURSI, hargaKursi)
                intentToResultActivity.putExtra(EXTRA_HARGA_TOTAL, hargaTotal)
                intentToResultActivity.putExtra(EXTRA_DATE, txtSelectTanggal.text.toString())
                intentToResultActivity.putExtra(EXTRA_WAKTU, txtSelectWaktu.text.toString())
                intentToResultActivity.putExtra(EXTRA_GAMBAR, savedImage)
                startActivity(intentToResultActivity)
            }
        }
    }

    private fun updateLable(myCalendar: Calendar){
        val myFormat = "EEEE, dd-MM-yyyy"
        val sdf= SimpleDateFormat(myFormat, Locale.UK)
        val formattedDate = sdf.format(myCalendar.time)
        memilihTanggal.text = formattedDate
    }
    private fun updateTimeLabel(myCalendar: Calendar) {
        val myFormat = "HH:mm"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        val formattedTime = sdf.format(myCalendar.time)
        memilihWaktu.text = formattedTime
    }

    private fun formatCurrency(value: Int): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("in","ID"))
        return formatter.format(value).replace("IDR","Rp.")
    }
}