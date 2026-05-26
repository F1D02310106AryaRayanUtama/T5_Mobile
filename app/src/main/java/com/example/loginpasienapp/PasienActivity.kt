package com.example.loginpasienapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpasienapp.adapter.PasienAdapter
import com.example.loginpasienapp.network.RetrofitClient
import kotlinx.coroutines.launch

class PasienActivity : AppCompatActivity() {

    private lateinit var textUserName: TextView
    private lateinit var progressCircular: ProgressBar
    private lateinit var errorLabelView: TextView
    private lateinit var rvPatientData: RecyclerView
    private lateinit var adapterPasien: PasienAdapter

    private var sessionKey: String = ""
    private var loggedInUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasien)

        // Mengambil kiriman data dari Intent
        sessionKey = intent.getStringExtra("TOKEN") ?: ""
        loggedInUser = intent.getStringExtra("NAMA_USER") ?: "User"

        // Hubungkan view dengan variabel
        textUserName = findViewById(R.id.tvNamaUser)
        progressCircular = findViewById(R.id.progressBar)
        errorLabelView = findViewById(R.id.tvError)
        rvPatientData = findViewById(R.id.rvPasien)

        // Set teks sambutan
        textUserName.text = "Selamat datang, $loggedInUser"

        // Inisialisasi daftar pasien
        adapterPasien = PasienAdapter()
        rvPatientData.layoutManager = LinearLayoutManager(this)
        rvPatientData.adapter = adapterPasien

        // Validasi keberadaan token
        if (sessionKey.isEmpty()) {
            displayError("Token tidak valid, silakan login ulang")
            return
        }

        // Mulai proses penarikan data
        loadPatientInformation()
    }

    private fun loadPatientInformation() {
        lifecycleScope.launch {
            toggleProgress(true)
            errorLabelView.visibility = View.GONE

            try {
                // Header Authorization dengan Bearer token
                val headerAuth = "Bearer $sessionKey"
                val networkResponse = RetrofitClient.apiInstance.retrievePatientList(headerAuth)

                if (networkResponse.isSuccessful) {
                    val responseBody = networkResponse.body()

                    if (responseBody != null && responseBody.success) {
                        val patientList = responseBody.data ?: emptyList()

                        if (patientList.isEmpty()) {
                            displayError("Belum ada data pasien")
                        } else {
                            // Kirim data ke adapter
                            adapterPasien.updateList(patientList)
                        }
                    } else {
                        displayError(responseBody?.message ?: "Gagal mengambil data pasien")
                    }
                } else if (networkResponse.code() == 401) {
                    displayError("Sesi habis, silakan login ulang")
                } else {
                    displayError("Terjadi galat: ${networkResponse.code()}")
                }

            } catch (err: Exception) {
                displayError("Koneksi gagal: ${err.message}")
            } finally {
                toggleProgress(false)
            }
        }
    }

    private fun toggleProgress(isVisible: Boolean) {
        progressCircular.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun displayError(info: String) {
        errorLabelView.text = info
        errorLabelView.visibility = View.VISIBLE
    }
}
