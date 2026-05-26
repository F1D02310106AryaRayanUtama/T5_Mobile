package com.example.loginpasienapp.model

data class PasienResponse(
    val success: Boolean,
    val message: String,
    val data: List<Pasien>?
)
