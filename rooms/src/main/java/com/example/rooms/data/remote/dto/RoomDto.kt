package com.example.rooms.data.remote.dto

import com.squareup.moshi.Json

data class RoomDto(
    val id: Int,
    @field:Json(name = "image_urls") val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    @field:Json(name = "price_per") val pricePer: String
)