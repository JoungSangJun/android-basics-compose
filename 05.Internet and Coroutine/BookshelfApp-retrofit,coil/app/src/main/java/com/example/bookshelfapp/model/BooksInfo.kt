package com.example.bookshelfapp.model

import kotlinx.serialization.Serializable

@Serializable
data class BooksInfo(
    val items: List<VolumeInfo>
)

@Serializable
data class VolumeInfo(
    val volumeInfo: BookInfo
)

@Serializable
data class BookInfo(
    val title: String,
    val authors: List<String>? = null,
    val publishedDate: String? = null,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String
)
