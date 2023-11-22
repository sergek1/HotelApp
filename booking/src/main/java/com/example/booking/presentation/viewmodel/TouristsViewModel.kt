package com.example.booking.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking.domain.model.Tourist
import com.example.resources.Result
import com.example.booking.domain.usecase.GetTouristsUseCase
import com.example.booking.domain.usecase.InsertTouristUseCase
import com.example.booking.domain.usecase.SaveTouristsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TouristsViewModel(
    private val getTouristsUseCase: GetTouristsUseCase,
    private val insertTouristUseCase: InsertTouristUseCase,
    private val saveTouristsUseCase: SaveTouristsUseCase
) : ViewModel() {

    private val _items = MutableStateFlow<Result<List<Tourist>>>(Result.Loading())
    val items: StateFlow<Result<List<Tourist>>> = _items.asStateFlow()

    init {
        getTourists()
    }

    fun getTourists() {
        viewModelScope.launch(Dispatchers.IO) {
            _items.value = getTouristsUseCase()
        }
    }

    fun insertTourist(tourist: Tourist) {
        viewModelScope.launch(Dispatchers.IO) {
            insertTouristUseCase(tourist)
        }
    }

    fun saveTourists(tourists: List<Tourist>) {
        viewModelScope.launch(Dispatchers.IO) {
            saveTouristsUseCase(tourists)
        }
    }
}