package com.example.booking.data.repository

import com.example.booking.data.mapper.asDataModel
import com.example.booking.data.mapper.asDomainModel
import com.example.booking.data.remote.BookingApi
import com.example.booking.domain.model.Booking
import com.example.booking.domain.model.Tourist
import com.example.booking.domain.repository.BookingRepository
import com.example.database.dao.TouristDao
import com.example.resources.Result

class BookingRepositoryImpl(
    private val api: BookingApi,
    private val dao: TouristDao
) : BookingRepository {
    override suspend fun getBooking(): Result<Booking> {
        return try {
            val result = api.getBooking()
            Result.Success(result.asDomainModel())
        } catch (e: Exception) {
            Result.Failure(e.message.toString())
        }
    }

    override suspend fun getTourists(): Result<List<Tourist>> {
        return try {
            val result = dao.getTourists()
            Result.Success(result.asDomainModel())
        } catch (e: Exception) {
            Result.Failure(e.message.toString())
        }
    }

    override suspend fun insertTourist(tourist: Tourist) {
        dao.insert(tourist.asDataModel())
    }

    override suspend fun saveTourists(tourists: List<Tourist>) {
        for (tourist in tourists) {
            dao.insert(tourist.asDataModel())
        }
    }

}