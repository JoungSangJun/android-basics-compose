package com.example.bookshelfapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookshelfapp.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelfapp.ui.screens.BooksViewModel
import com.example.bookshelfapp.ui.screens.HomeScreen

@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val booksViewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
            HomeScreen(
                retryAction = booksViewModel::getBooksInfo,
                booksUiState = booksViewModel.booksUiState
            )
        }
    }
}