/*
 * 2023년 1월 23일
 * Android-Developers
 * 단원 3: 스크롤 가능한 목록 추가
 * Column과 LazyColumn 차이
 * Column: Compose가 한 번에 모두 로드하므로 표시할 항목이 적은 경우 Column을 사용
 * LazyColumn: 주문형 콘텐츠를 추가할 수 있어 긴 목록의 경우 특히 목록의 길이를 알 수 없을 때 유용, 자동 스크롤 제공
 */
package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationApp()
        }
    }
}

/*
 * 이미지와 텍스트를 화면에 출력
 *
 * @param affirmation : 이미지와 텍스트에서 사용할 stringResourceId와 drawableResourceId 전달
 * @param modifier : 생성자
 */

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Column() {
            Image(
                painter = painterResource(id = affirmation.drawableResourceId),
                contentDescription = stringResource(
                    id = affirmation.stringResourceId
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun AffirmationApp() {
    AffirmationsTheme {
        AffirmationList(affirmationList = Datasource().loadAffirmations())
    }
}

/*
 * affirmation을 List형태로 화면에 출력
 *
 * @param affirmationList : 화면에 출력할 Affirmation 정보를 List형태로 받음
 * @param modifier : 생성자
 */

@Composable
private fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(affirmationList) { affirmation ->
            AffirmationCard(affirmation)
        }
    }
}

@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
}