package com.example.bookshelfapp.network

import com.example.bookshelfapp.model.BooksInfo
import retrofit2.http.GET
import retrofit2.http.Query


interface BooksApiService {
    @GET("books/v1/volumes")
    suspend fun getBooksInfo(@Query("q") query: String): BooksInfo
}