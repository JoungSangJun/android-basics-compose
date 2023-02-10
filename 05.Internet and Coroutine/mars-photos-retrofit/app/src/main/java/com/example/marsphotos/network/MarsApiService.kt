package com.example.marsphotos.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET

/*
 * data source class
 * Retrofit을 사용하여 web server에서 data 가져옴
 */

// data 가저올 서버 URL
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

// retrofit 인스턴스 생성
private val retrofit =
    Retrofit.Builder().addConverterFactory(
        Json.asConverterFactory(MediaType.get("application/jason"))
    ).baseUrl(BASE_URL).build()


// retrofitService 초기화
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}

// retrofit이 http를 활용하여 웹서버와 통신위한 interface
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}