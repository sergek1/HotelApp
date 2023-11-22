package com.example.rooms.domain.repository

import com.example.resources.Result
import com.example.rooms.domain.model.Room

interface RoomsRepository {
    suspend fun getRooms(): Result<List<Room>>
}