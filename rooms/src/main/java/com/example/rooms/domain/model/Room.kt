package com.example.rooms.domain.model

data class Room(
    val id: Int? = null,
    val name: String? = null,
    val price: Int? = null,
    val pricePer: String? = null,
    val peculiarities: List<String> = emptyList(),
    val imageUrls: List<String> = emptyList()
)
