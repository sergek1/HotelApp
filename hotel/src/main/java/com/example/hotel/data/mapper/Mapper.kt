package com.example.hotel.data.mapper

import com.example.hotel.data.remote.dto.HotelDto
import com.example.hotel.domain.model.Hotel

fun HotelDto.asDomainModel(): Hotel {
    return Hotel(
        id = id,
        name = name,
        address = address,
        minimalPrice = minimalPrice,
        priceForIt = priceForIt,
        rating = rating,
        ratingName = ratingName,
        imageUrls = imageUrls,
        description = aboutTheHotel.description,
        peculiarities = aboutTheHotel.peculiarities
    )
}