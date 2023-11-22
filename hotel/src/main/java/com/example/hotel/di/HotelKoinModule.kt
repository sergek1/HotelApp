package com.example.hotel.di

import com.example.hotel.data.remote.HotelApi
import com.example.hotel.data.repository.HotelRepositoryImpl
import com.example.hotel.domain.repository.HotelRepository
import com.example.hotel.domain.usecase.GetHotelUseCase
import com.example.hotel.presentation.viewmodel.HotelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val hotelPresentationModule = module {
    viewModel { HotelViewModel(getHotelUseCase = get()) }
}

val hotelDomainModule = module {
    factory { GetHotelUseCase(repository =  get()) }
}

val hotelDataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(HotelApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(HotelApi::class.java)
    }


    single {
        HotelRepositoryImpl(
            api = get(),
        ) as HotelRepository
    }
}

val hotelKoinModules = listOf(
    hotelPresentationModule,
    hotelDomainModule,
    hotelDataModule
)