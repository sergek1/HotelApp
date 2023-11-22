package com.example.hotel.data.repository

import com.example.hotel.data.mapper.asDomainModel
import com.example.hotel.data.remote.HotelApi
import com.example.hotel.domain.model.Hotel
import com.example.hotel.domain.repository.HotelRepository
import com.example.resources.Result


class HotelRepositoryImpl(
    private val api: HotelApi,
) : HotelRepository {
    override suspend fun getHotel(): Result<Hotel> {
        return try {
            val result = api.getHotel()
            Result.Success(result.asDomainModel())
        } catch (e: Exception) {
            Result.Failure(e.message.toString())
        }
    }
}