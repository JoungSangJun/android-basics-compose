package com.example.bookshelfapp.data

import com.example.bookshelfapp.model.BooksInfo
import com.example.bookshelfapp.network.BooksApiService

interface BooksInfoRepository {
    suspend fun getBooksInfo(query: String): BooksInfo
}

class NetworkMarsPhotosRepository(
    private val booksApiService: BooksApiService
) : BooksInfoRepository {

    override suspend fun getBooksInfo(query: String): BooksInfo =
        booksApiService.getBooksInfo(query)
}