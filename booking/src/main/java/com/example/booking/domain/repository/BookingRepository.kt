package com.example.booking.domain.repository

import com.example.booking.domain.model.Booking
import com.example.booking.domain.model.Tourist
import com.example.resources.Result

interface BookingRepository {

    suspend fun getBooking(): Result<Booking>

    suspend fun getTourists(): Result<List<Tourist>>

    suspend fun insertTourist(tourist: Tourist)

    suspend fun saveTourists(tourists: List<Tourist>)

}