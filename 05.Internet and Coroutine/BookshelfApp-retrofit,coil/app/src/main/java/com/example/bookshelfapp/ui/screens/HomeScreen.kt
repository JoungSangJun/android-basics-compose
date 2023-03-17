package com.example.bookshelfapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelfapp.R
import com.example.bookshelfapp.model.VolumeInfo
import kotlin.reflect.KFunction1


@Composable
fun HomeScreen(
    retryAction: KFunction1<String, Unit>,
    booksUiState: BooksUiState,
    modifier: Modifier = Modifier
) {

    var bookTitle by remember { mutableStateOf("") }

    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(20.dp)
        ) {
            TextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                placeholder = { Text("책 이름 검색") },
            )
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = ({ retryAction(bookTitle) }),
                modifier = Modifier
                    .width(80.dp)
                    .height(55.dp)
            ) {
                Text(text = "검색", fontSize = 15.sp)
            }
        }
        when (booksUiState) {
            is BooksUiState.Loading -> LoadingScreen(modifier)
            is BooksUiState.Success -> PhotoGridScreen(
                booksUiState.booksInfo.items,
                modifier
            )
            is BooksUiState.Error -> ErrorScreen(retryAction, modifier)
            is BooksUiState.Initial -> InitialScreen()
        }
    }
}

@Composable
fun InitialScreen(){

}

@Composable
fun PhotoGridScreen(volumeInfo: List<VolumeInfo>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(volumeInfo) {
            BooksPhotoCard(it)
        }
    }
}

@Composable
fun BooksPhotoCard(volumeInfo: VolumeInfo, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Card(
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(300.dp)
                .clickable { expanded = !expanded },
            elevation = 8.dp,
        ) {
            val url = volumeInfo.volumeInfo.imageLinks?.smallThumbnail?.replace("http", "https")
                ?: R.drawable.ic_broken_image
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(url)
                    .crossfade(true).build(),
                contentDescription = volumeInfo.volumeInfo.title,
                contentScale = ContentScale.FillHeight,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
        }
        if (expanded) {
            ShowBooksInfo(volumeInfo = volumeInfo)
        }
    }

}

@Composable
fun ShowBooksInfo(volumeInfo: VolumeInfo) {
    Text(text = "Title : ", fontWeight = FontWeight.Bold)
    Text(volumeInfo.volumeInfo.title)
    Text(text = "authors : ", fontWeight = FontWeight.Bold)
    Text(volumeInfo.volumeInfo.authors?.get(0) ?: "X")
    Text(
        text = "publishedDate : ",
        fontWeight = FontWeight.Bold
    )
    volumeInfo.volumeInfo.publishedDate?.let { Text(it) }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = "로딩중"
        )
    }
}

@Composable
fun ErrorScreen(retryAction: KFunction1<String, Unit>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = { retryAction("cicero") }) {
            Text(stringResource(R.string.retry))
        }
    }
}
