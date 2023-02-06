package com.example.incheontour.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.incheontour.R
import com.example.incheontour.data.TourUiStateList
import com.example.incheontour.model.TourUiState

/*
 * Category와 Recommend를 선택하면
 * 선택한 장소의 자세한 정보 출력
 */

/**
 * 사용자가 궁금한 장소에 대한 자세한 정보 출력
 *
 * @param windowSize : 사용자가 사용중인 핸드폰 화면 크기(Compact, Medium, Expanded)
 * @param tourUiState : 사용자가 선택한 장소에 대한 정보를 가진 data Class
 * @param onPreviousButtonClick : Pre버튼 클릭 이벤트
 * @param onCancelButtonClick : Cancel버튼 클릭 이벤트
 */

@Composable
fun PlaceInfoScreen(
    windowSize: WindowWidthSizeClass,
    tourUiState: TourUiState,
    onPreviousButtonClick: () -> Unit = {},
    onCancelButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
            .background(MaterialTheme.colors.surface)
    ) {
        Card(modifier = Modifier.padding(30.dp)) {
            Column() {
                Image(
                    painter = painterResource(id = tourUiState.image!!),
                    contentDescription = ""
                )
                Text(
                    text = "Category : ${tourUiState.category}",
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(text = "Recommend : ${tourUiState.recommend}")
                Text(text = "주소 : ${tourUiState.address}")
                Text(text = "영업시간 : ${tourUiState.businessHours}")
                Text(text = "전화번호 : ${tourUiState.tel}")
            }
            Spacer(modifier = Modifier.weight(1f))
        }


        // windowSize가 Compact or Medium 일때만 버튼 생성
        if (windowSize == WindowWidthSizeClass.Medium || windowSize == WindowWidthSizeClass.Compact) {
            PlaceInfoButtonGroup(
                onPreviousButtonClick = onPreviousButtonClick,
                onCancelButtonClick = onCancelButtonClick
            )
        }
    }


}


/**
 * PlaceInfoScreen Button 2개 출력
 *
 * @param onPreviousButtonClick : Pre버튼 클릭 이벤트
 * @param onCancelButtonClick : Cancel버튼 클릭 이벤트
 */
@Composable
fun PlaceInfoButtonGroup(
    onPreviousButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = onPreviousButtonClick, modifier = Modifier.weight(1f)) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous")
            Text(text = stringResource(id = R.string.previous))
        }
        Button(
            onClick = onCancelButtonClick,
            modifier = Modifier.weight(1f),
        ) {
            Text(text = stringResource(id = R.string.Cancel))
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Cancel")
        }
    }
}

@Preview
@Composable
fun PlaceInfoScreenPreView() {
    PlaceInfoScreen(
        windowSize = WindowWidthSizeClass.Compact,
        tourUiState = TourUiState(
            category = "음식점",
            recommend = "나운순대",
            tel = TourUiStateList.cafe[0].tel,
            image = TourUiStateList.cafe[0].image,
            address = TourUiStateList.cafe[0].address,
            businessHours = TourUiStateList.cafe[0].businessHours
        ),
        onPreviousButtonClick = {},
        onCancelButtonClick = {}
    )
}