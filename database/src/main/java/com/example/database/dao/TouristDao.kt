package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.TouristEntity

@Dao
interface TouristDao {

    @Query("SELECT * FROM table_tourist")
    suspend fun getTourists(): List<TouristEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tourist: TouristEntity)
}
