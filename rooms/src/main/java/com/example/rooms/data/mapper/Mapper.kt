package com.example.rooms.data.mapper

import com.example.rooms.data.remote.dto.RoomsDto
import com.example.rooms.domain.model.Room

fun RoomsDto.asDomainModel(): List<Room> {
    return rooms.map {
        Room(
            id = it.id,
            name = it.name,
            price = it.price,
            pricePer = it.pricePer,
            peculiarities = it.peculiarities,
            imageUrls = it.imageUrls,
        )
    }
}