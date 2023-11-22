package com.example.rooms.data.repository

import com.example.resources.Result
import com.example.rooms.data.mapper.asDomainModel
import com.example.rooms.data.remote.RoomsApi
import com.example.rooms.domain.model.Room
import com.example.rooms.domain.repository.RoomsRepository

class RoomsRepositoryImpl(
    private val api: RoomsApi,
//    private val dao: RoomsDao,
) : RoomsRepository {
    override suspend fun getRooms(): Result<List<Room>> {
        return try {
            val result = api.getRooms()
            Result.Success(result.asDomainModel())
        } catch (e: Exception) {
            Result.Failure(e.message.toString())
        }
    }
}