package com.powerdino.splatoon3_companion.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.powerdino.splatoon3_companion.service.RetroFitService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val splatoonRepository:SplatoonRepository
}

class DefaultAppContainer:AppContainer{
    private val baseUrl = "https://splatoon.oatmealdome.me/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
        Json{
            ignoreUnknownKeys = true
            coerceInputValues = true
        }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retroFitService:RetroFitService by lazy{
        retrofit.create(RetroFitService::class.java)
    }

    override val splatoonRepository: SplatoonRepository by lazy {
        NetworkSplatoonRepository(retroFitService)
    }
}