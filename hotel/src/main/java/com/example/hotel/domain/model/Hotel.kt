package com.example.hotel.domain.model

data class Hotel(
    val id: Int? = null,
    val name: String? = null,
    val address: String? = null,
    val minimalPrice: Int? = null,
    val priceForIt: String? = null,
    val rating: Int? = null,
    val ratingName: String? = null,
    val imageUrls: List<String> = emptyList(),
    val description: String,
    val peculiarities: List<String>
)
