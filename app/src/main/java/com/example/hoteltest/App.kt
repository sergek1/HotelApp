package com.example.hoteltest

import android.app.Application
import com.example.booking.di.bookingKoinModules
import com.example.database.di.databaseModule
import com.example.hotel.di.hotelKoinModules
import com.example.rooms.di.roomsKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(hotelKoinModules + roomsKoinModules + bookingKoinModules + databaseModule)
        }
    }
}