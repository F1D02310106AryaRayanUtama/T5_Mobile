package com.example.loginpasienapp.model

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: LoginData?
)
