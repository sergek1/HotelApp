package com.example.booking.di

import com.example.booking.data.remote.BookingApi
import com.example.booking.data.repository.BookingRepositoryImpl
import com.example.booking.domain.repository.BookingRepository
import com.example.booking.domain.usecase.GetBookingUseCase
import com.example.booking.domain.usecase.GetTouristsUseCase
import com.example.booking.domain.usecase.InsertTouristUseCase
import com.example.booking.domain.usecase.SaveTouristsUseCase
import com.example.booking.presentation.viewmodel.BookingViewModel
import com.example.booking.presentation.viewmodel.TouristsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val bookingPresentationModule = module {

    viewModel { BookingViewModel(getBookingUseCase = get()) }

    viewModel {
        TouristsViewModel(
            getTouristsUseCase = get(),
            insertTouristUseCase = get(),
            saveTouristsUseCase = get()
        )
    }
}

val bookingDomainModule = module {
    factory { GetBookingUseCase(repository = get()) }
    factory { GetTouristsUseCase(repository = get()) }
    factory { InsertTouristUseCase(repository = get()) }
    factory { SaveTouristsUseCase(repository = get()) }
}

val bookingDataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BookingApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BookingApi::class.java)
    }


    single {
        BookingRepositoryImpl(
            api = get(),
            dao = get()
        ) as BookingRepository
    }
}

val bookingKoinModules = listOf(
    bookingDataModule,
    bookingDomainModule,
    bookingPresentationModule
)