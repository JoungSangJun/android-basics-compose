package com.example.bookshelfapp.data

import com.example.bookshelfapp.network.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val booksPhotosRepository: BooksInfoRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "https://www.googleapis.com"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }


    override val booksPhotosRepository: BooksInfoRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}

