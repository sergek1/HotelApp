package com.example.hotel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.hotel.domain.model.Hotel
import com.example.hotel.domain.usecase.GetHotelUseCase
import com.example.resources.Result

class HotelViewModel(
    private val getHotelUseCase: GetHotelUseCase
) : ViewModel() {
    var hotel: LiveData<Result<Hotel>> = liveData(context = viewModelScope.coroutineContext) {
        emit(Result.Loading())
    }

    fun getHotel() {
        hotel = liveData(context = viewModelScope.coroutineContext) {
            emit(Result.Loading())
            val result = getHotelUseCase()
            emit(result)
        }
    }
}