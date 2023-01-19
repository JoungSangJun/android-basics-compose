package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

/*
 * 2023년 1월 19일
 * Android-Developers (호이스팅 실습)
 * 단원 2: Art Gallery
 *  * 버튼을 누르면 배열에 저장된 이미지 순서대로 이미지 변경
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    // 앱 화면에 출력

    // 이미지 배열
    val image = arrayOf(R.drawable.monariza, R.drawable.sunflower, R.drawable.thescream)
    // 이미지 제목 배열
    val imageDescription = arrayOf(
        R.string.image_description_monariza,
        R.string.image_description_sunflowers,
        R.string.image_description_the_scream
    )
    // 이미지 작가 배열
    val imageAuthor = arrayOf(
        R.string.image_author_leonardo,
        R.string.image_author_vincent_van_gogh,
        R.string.image_author_edvard_munch
    )

    // image, imageDescription, imageAuthor의 index 값 (0~2)
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(horizontal = 60.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        drawImage(
            painter = image[count],
            contentDescription = R.string.image_description_monariza
        )
        Spacer(modifier = Modifier.height(40.dp))

        Text(text = stringResource(imageDescription[count]), fontSize = 30.sp)
        Text(
            text = stringResource(imageAuthor[count]),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(100.dp))
        ButtonTheRow(
            {
                // previous 버튼 누르면 index - 1 (0~2)
                if (count == 0) {
                    count = 2
                } else {
                    count--
                }
            },
            {
                // next 버튼 누르면 index + 1 (0~2)
                if (count == 2) {
                    count = 0
                } else {
                    count++
                }
            }
        )


    }
}

/*
 * previous 버튼, next 버튼 클릭 시 이미지 배열 index +1, -1 씩 이동
 *
 * @param onClickPre : previous 버튼 클릭 람다
 * @param onClickNext : next 버튼 클릭 람다
 */
@Composable
fun ButtonTheRow(onClickPre: () -> Unit, onClickNext: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onClickPre, modifier = Modifier.size(100.dp, 40.dp)) {
            Text(text = "Previous")
        }
        Button(
            onClick = onClickNext,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .size(100.dp, 40.dp)
        ) {
            Text(text = "Next")
        }
    }
}

/*
 * 앱 중앙에 있는 이미지 출력
 *
 * @param painter : 이미지 ID
 * @param contentDescription : 이미지 contentDescription
 */

@Composable
fun drawImage(painter: Int, contentDescription: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = painter),
        contentDescription = stringResource(id = contentDescription),
        modifier = modifier
            .size(300.dp, 400.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}