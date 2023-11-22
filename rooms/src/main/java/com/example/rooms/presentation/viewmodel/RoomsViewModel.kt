package com.example.rooms.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.rooms.domain.model.Room
import com.example.resources.Result
import com.example.rooms.domain.usecase.GetRoomsUseCase

class RoomsViewModel(
    private val getRoomsUseCase: GetRoomsUseCase
) : ViewModel() {

    val items: LiveData<Result<List<Room>>> = liveData(context = viewModelScope.coroutineContext) {
        emit(Result.Loading())
        val result = getRoomsUseCase()
        emit(result)
    }
}