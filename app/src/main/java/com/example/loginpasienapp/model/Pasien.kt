package com.example.loginpasienapp.model

data class Pasien(
    val id: Int,
    val nama: String,
    val tanggal_lahir: String,
    val jenis_kelamin: String,
    val alamat: String,
    val no_telepon: String,
    val created_at: String? = null,
    val updated_at: String? = null
)
