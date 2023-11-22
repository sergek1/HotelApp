package com.example.booking.data.remote.dto

import com.squareup.moshi.Json

data class BookingDto(
    @field:Json(name = "arrival_country") val arrivalCountry: String,
    val departure: String,
    @field:Json(name = "fuel_charge") val fuelCharge: Int,
    val horating: Int,
    @field:Json(name = "hotel_adress") val hotelAddress: String,
    @field:Json(name = "hotel_name") val hotelName: String,
    val id: Int,
    @field:Json(name = "number_of_nights") val numberOfNights: Int,
    val nutrition: String,
    @field:Json(name = "rating_name") val ratingName: String,
    val room: String,
    @field:Json(name = "service_charge") val serviceCharge: Int,
    @field:Json(name = "tour_date_start") val tourDateStart: String,
    @field:Json(name = "tour_date_stop") val tourDateStop: String,
    @field:Json(name = "tour_price") val tourPrice: Int
)
