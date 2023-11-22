package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tourist")
data class TouristEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null,
    var lastname: String? = null,
    var birthday: String? = null,
    var citizenship: String? = null,
    var interPassportNumber: String? = null,
    var periodOfPassport: String? = null
)
