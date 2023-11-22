package com.example.hotel.domain.repository

import com.example.hotel.domain.model.Hotel
import com.example.resources.Result

interface HotelRepository {
    suspend fun getHotel(): Result<Hotel>
}