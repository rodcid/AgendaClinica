package com.example.fichasclinicas.services

import com.example.fichasclinicas.models.LoginPayloadDTO
import com.example.fichasclinicas.models.LoginResponseDTO
import com.example.fichasclinicas.models.RegisterPayloadDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/local")
    fun login(@Body payload: LoginPayloadDTO): Call<LoginResponseDTO>

    @POST("/api/auth/local/register")
    fun register(@Body payload: RegisterPayloadDTO): Call<Void>
}