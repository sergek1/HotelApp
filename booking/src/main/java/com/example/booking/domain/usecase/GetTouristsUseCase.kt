package com.example.booking.domain.usecase

import com.example.booking.domain.model.Tourist
import com.example.resources.Result
import com.example.booking.domain.repository.BookingRepository

class GetTouristsUseCase(private val repository: BookingRepository) {
    suspend operator fun invoke(): Result<List<Tourist>> {
        return repository.getTourists()
    }
}