package com.example.booking.data.remote

import com.example.booking.data.remote.dto.BookingDto
import retrofit2.http.GET

interface BookingApi {

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBooking(): BookingDto

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}