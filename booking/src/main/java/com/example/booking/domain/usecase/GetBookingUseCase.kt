package com.example.booking.domain.usecase

import com.example.booking.domain.model.Booking
import com.example.resources.Result
import com.example.booking.domain.repository.BookingRepository

class GetBookingUseCase (private val repository: BookingRepository) {
    suspend operator fun invoke(): Result<Booking> {
        return repository.getBooking()
    }
}