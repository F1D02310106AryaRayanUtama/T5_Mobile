package com.example.loginpasienapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.loginpasienapp.model.LoginRequest
import com.example.loginpasienapp.network.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var fieldEmail: TextInputEditText
    private lateinit var fieldPassword: TextInputEditText
    private lateinit var actionLogin: Button
    private lateinit var loader: ProgressBar
    private lateinit var textError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Binding widget ke properti class
        fieldEmail = findViewById(R.id.etEmail)
        fieldPassword = findViewById(R.id.etPassword)
        actionLogin = findViewById(R.id.btnLogin)
        loader = findViewById(R.id.progressBar)
        textError = findViewById(R.id.tvError)

        actionLogin.setOnClickListener {
            runLoginProcess()
        }
    }

    private fun runLoginProcess() {
        val emailValue = fieldEmail.text.toString().trim()
        val passValue = fieldPassword.text.toString().trim()

        // Validasi isian form
        if (emailValue.isEmpty()) {
            fieldEmail.error = "Email tidak boleh kosong"
            fieldEmail.requestFocus()
            return
        }

        if (passValue.isEmpty()) {
            fieldPassword.error = "Password tidak boleh kosong"
            fieldPassword.requestFocus()
            return
        }

        // Sembunyikan pesan kesalahan sebelumnya
        textError.visibility = View.GONE

        // Melakukan request login via Retrofit
        lifecycleScope.launch {
            setViewState(true)

            try {
                val credentials = LoginRequest(email = emailValue, password = passValue)
                val call = RetrofitClient.apiInstance.attemptUserLogin(credentials)

                if (call.isSuccessful) {
                    val result = call.body()

                    if (result != null && result.success) {
                        val sessionToken = result.data?.token ?: ""
                        val nameOfUser = result.data?.user?.name ?: "User"

                        // Berpindah ke activity pasien
                        val nextScreen = Intent(this@LoginActivity, PasienActivity::class.java).apply {
                            putExtra("TOKEN", sessionToken)
                            putExtra("NAMA_USER", nameOfUser)
                        }
                        startActivity(nextScreen)
                        finish() 
                    } else {
                        displayMessage(result?.message ?: "Login gagal")
                    }
                } else {
                    displayMessage("Email atau password salah")
                }

            } catch (ex: Exception) {
                displayMessage("Gagal terhubung ke server: ${ex.message}")
            } finally {
                setViewState(false)
            }
        }
    }

    private fun setViewState(isLoading: Boolean) {
        loader.visibility = if (isLoading) View.VISIBLE else View.GONE
        actionLogin.isEnabled = !isLoading
    }

    private fun displayMessage(msg: String) {
        textError.text = msg
        textError.visibility = View.VISIBLE
    }
}
