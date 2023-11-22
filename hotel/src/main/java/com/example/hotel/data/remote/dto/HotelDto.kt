package com.example.hotel.data.remote.dto

import com.squareup.moshi.Json

data class HotelDto(
    @field:Json(name = "about_the_hotel") val aboutTheHotel: AboutTheHotel,
    val name: String,
    @field:Json(name = "adress") val address: String,
    val id: Int,
    @field:Json(name = "image_urls") val imageUrls: List<String>,
    @field:Json(name = "minimal_price") val minimalPrice: Int,
    @field:Json(name = "price_for_it") val priceForIt: String,
    val rating: Int,
    @field:Json(name = "rating_name") val ratingName: String
)
