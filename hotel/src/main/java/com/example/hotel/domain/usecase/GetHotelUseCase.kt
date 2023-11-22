package com.example.hotel.domain.usecase

import com.example.hotel.domain.model.Hotel
import com.example.resources.Result
import com.example.hotel.domain.repository.HotelRepository

class GetHotelUseCase(private val repository: HotelRepository) {
    suspend operator fun invoke(): Result<Hotel> {
        return repository.getHotel()
    }
}