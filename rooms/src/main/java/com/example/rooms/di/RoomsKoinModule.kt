package com.example.rooms.di

import com.example.rooms.data.remote.RoomsApi
import com.example.rooms.data.repository.RoomsRepositoryImpl
import com.example.rooms.domain.repository.RoomsRepository
import com.example.rooms.domain.usecase.GetRoomsUseCase
import com.example.rooms.presentation.viewmodel.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val roomsPresentationModule = module {
    viewModel { RoomsViewModel(getRoomsUseCase = get()) }
}

val roomsDomainModule = module {
    factory { GetRoomsUseCase(repository =  get()) }
}

val roomsDataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(RoomsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RoomsApi::class.java)
    }


    single {
        RoomsRepositoryImpl(
            api = get(),
        ) as RoomsRepository
    }
}

val roomsKoinModules = listOf(
    roomsPresentationModule,
    roomsDomainModule,
    roomsDataModule
)