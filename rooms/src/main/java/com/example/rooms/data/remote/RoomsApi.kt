package com.example.rooms.data.remote

import com.example.rooms.data.remote.dto.RoomsDto
import retrofit2.http.GET

interface RoomsApi {

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): RoomsDto

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}