package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.TouristDao
import com.example.database.model.TouristEntity

@Database(entities = [TouristEntity::class], version = 1)
abstract class HotelDatabase : RoomDatabase(){

    abstract fun touristDao(): TouristDao

}