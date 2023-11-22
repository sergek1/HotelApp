package com.example.booking.domain.usecase

import com.example.booking.domain.model.Tourist
import com.example.booking.domain.repository.BookingRepository

class SaveTouristsUseCase(private val repository: BookingRepository) {
    suspend operator fun invoke(tourists: List<Tourist>) {
        return repository.saveTourists(tourists)
    }
}