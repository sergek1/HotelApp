package com.example.hotel.data.remote

import com.example.hotel.data.remote.dto.HotelDto
import retrofit2.http.GET

interface HotelApi {

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): HotelDto

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}