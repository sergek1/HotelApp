package com.example.rooms.domain.usecase

import com.example.rooms.domain.model.Room
import com.example.resources.Result
import com.example.rooms.domain.repository.RoomsRepository

class GetRoomsUseCase(private val repository: RoomsRepository) {
    suspend operator fun invoke(): Result<List<Room>> {
        return repository.getRooms()
    }
}