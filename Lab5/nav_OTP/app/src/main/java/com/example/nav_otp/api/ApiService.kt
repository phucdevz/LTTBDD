package com.example.nav_otp.api

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/request-reset")
    suspend fun requestPasswordReset(@Body request: PasswordResetRequest): PasswordResetResponse

    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: OtpVerificationRequest): OtpVerificationResponse
}

data class PasswordResetRequest(
    val email: String
)

data class PasswordResetResponse(
    val message: String
)

data class OtpVerificationRequest(
    val email: String,
    val otp: String
)

data class OtpVerificationResponse(
    val message: String,
    val resetToken: String
) 