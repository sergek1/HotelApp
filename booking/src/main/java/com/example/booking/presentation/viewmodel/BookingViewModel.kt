package com.example.booking.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.booking.domain.model.Booking
import com.example.resources.Result
import com.example.booking.domain.usecase.GetBookingUseCase

class BookingViewModel(
    private val getBookingUseCase: GetBookingUseCase
) : ViewModel() {
    private var phoneNumber: String = ""
    private var email: String = ""

    var booking: LiveData<Result<Booking>> =
        liveData(context = viewModelScope.coroutineContext) {
            emit(Result.Loading())
        }

    fun getBooking() {
        booking = liveData(context = viewModelScope.coroutineContext) {
            emit(Result.Loading())
            val result = getBookingUseCase()
            emit(result)
        }
    }

    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }

    fun getPhoneNumber(): String {
        return phoneNumber
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail(): String {
        return email
    }
}