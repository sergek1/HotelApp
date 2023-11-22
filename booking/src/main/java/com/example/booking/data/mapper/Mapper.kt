package com.example.booking.data.mapper

import com.example.booking.data.remote.dto.BookingDto
import com.example.booking.domain.model.Booking
import com.example.booking.domain.model.Tourist
import com.example.database.model.TouristEntity

fun BookingDto.asDomainModel(): Booking {
    return Booking(
        id = id,
        hotelName = hotelName,
        hotelAddress = hotelAddress,
        horating = horating,
        ratingName = ratingName,
        departure = departure,
        arrivalCountry = arrivalCountry,
        tourDateStart = tourDateStart,
        tourDateStop = tourDateStop,
        numberOfNights = numberOfNights,
        room = room,
        nutrition = nutrition,
        tourPrice = tourPrice,
        fuelCharge = fuelCharge,
        serviceCharge = serviceCharge
    )
}

fun List<TouristEntity>.asDomainModel(): List<Tourist> {
    return map {
        Tourist(
            id = it.id,
            name = it.name,
            lastname = it.lastname,
            birthday = it.birthday,
            citizenship = it.citizenship,
            interPassportNumber = it.interPassportNumber,
            periodOfPassport = it.periodOfPassport
        )
    }
}

fun Tourist.asDataModel(): TouristEntity {
    return TouristEntity(
        id = id,
        name = name,
        lastname = lastname,
        birthday = birthday,
        citizenship = citizenship,
        interPassportNumber = interPassportNumber,
        periodOfPassport = periodOfPassport
    )
}

