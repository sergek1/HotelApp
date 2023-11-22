package com.example.booking.domain.usecase

import com.example.booking.domain.model.Tourist
import com.example.booking.domain.repository.BookingRepository

class InsertTouristUseCase(private val repository: BookingRepository) {
    suspend operator fun invoke(tourist: Tourist) {
        return repository.insertTourist(tourist)
    }
}