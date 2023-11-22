package com.example.database.di

import androidx.room.Room
import com.example.database.HotelDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), HotelDatabase::class.java, "hotel_database").build()
    }

    single { get<HotelDatabase>().touristDao() }
}