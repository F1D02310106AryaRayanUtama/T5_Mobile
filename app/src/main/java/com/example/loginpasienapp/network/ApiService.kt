package com.example.loginpasienapp.network

import com.example.loginpasienapp.model.LoginRequest
import com.example.loginpasienapp.model.LoginResponse
import com.example.loginpasienapp.model.PasienResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun attemptUserLogin(
        @Body loginCredentials: LoginRequest
    ): Response<LoginResponse>

    @GET("pasien")
    suspend fun retrievePatientList(
        @Header("Authorization") bearerToken: String
    ): Response<PasienResponse>
}
