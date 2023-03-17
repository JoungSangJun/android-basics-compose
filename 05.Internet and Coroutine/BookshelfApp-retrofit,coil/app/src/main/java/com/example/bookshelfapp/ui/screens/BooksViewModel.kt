package com.example.bookshelfapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelfapp.BooksInfoApplication
import com.example.bookshelfapp.data.BooksInfoRepository
import com.example.bookshelfapp.model.BooksInfo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BooksUiState {
    data class Success(val booksInfo: BooksInfo) : BooksUiState
    object Error : BooksUiState
    object Loading : BooksUiState
    object Initial : BooksUiState
}

class BooksViewModel(private val booksInfoRepository: BooksInfoRepository) : ViewModel() {
    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Initial)
        private set

    fun getBooksInfo(title: String) {
        viewModelScope.launch {
            booksUiState = try {
                val listResult = booksInfoRepository.getBooksInfo(title)
                BooksUiState.Success(listResult)
            } catch (e: IOException) {
                BooksUiState.Error
            } catch (e: HttpException) {
                BooksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksInfoApplication)
                val booksInfoRepository = application.container.booksPhotosRepository
                BooksViewModel(booksInfoRepository = booksInfoRepository)
            }
        }
    }

}