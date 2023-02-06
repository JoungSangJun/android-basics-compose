package com.example.incheontour.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.incheontour.R
import com.example.incheontour.data.DataSource
import com.example.incheontour.data.TourItem

/**
 * Recommand화면과 Category화면 UI구성
 *
 * @param options : Category Item or Recommend Item 받아와 화면에 출력
 * @param onPreviousButtonClick : Previous 클릭시 백스택에 쌓여있는 이전 화면으로 돌아감
 * @param onNextButtonClick : NextButton 클릭시 RadioButton으로 선택한 값 저장 및 다음화면 전환
 * @param onSelectionChanged : RadioButton 선택시 UiState 값 변수
 * @param windowSize : 사용자 화면 크기
 */

@Composable
fun BaseMenuScreen(
    options: List<TourItem>,
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onSelectionChanged: (TourItem) -> Unit,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {

    var selectedItemName by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        options.forEach { item ->
            CategoryAndRecommendItemRow(
                item = item,
                selectedItemName = selectedItemName,
                onSelectionChanged = onSelectionChanged,
                onSelectionItemChanged = { selectedItemName = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // windowSize가 Compact or Medium 일때만 버튼 생성
        if (windowSize == WindowWidthSizeClass.Medium || windowSize == WindowWidthSizeClass.Compact) {
            MenuScreenButtonGroup(
                selectedItemName = selectedItemName,
                onPreviousButtonClick = onPreviousButtonClick,
                onNextButtonClick = onNextButtonClick,
                modifier = modifier
            )
        }
    }
}

/**
 * Pre 버튼과 Next 버튼 생성 메소드
 *
 * @param selectedItemName : RadioButton 클릭 Text, selectedItemName 있어야 NextButton 활성화
 * @param onPreviousButtonClick : Pre버튼 클릭 이벤트
 * @param onNextButtonClick : Next버튼 클릭 이벤트
 */

@Composable
fun MenuScreenButtonGroup(
    selectedItemName: String,
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
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
            onClick = onNextButtonClick,
            modifier = Modifier.weight(1f),
            enabled = selectedItemName.isNotEmpty()
        ) {
            Text(text = stringResource(id = R.string.Next))
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next")
        }
    }
}

/**
 * Category와 Recommend 아이템 하나씩 받아와
 * 라디오 버튼과 화면에 출력
 *
 * @param item : TourItem 하나씩 받아옴
 * @param selectedItemName : RadioButton 선택한 칸의 이름(커피숍 or 음식점 or 쇼핑몰 or 공원)
 * @param onSelectionItemChanged : RadioButton 선택 변경시 selectedItemName 이름 변경
 * @param onSelectionChanged : RadioButton 선택 변경시 TourUiState category 변경경 */
@Composable
fun CategoryAndRecommendItemRow(
    item: TourItem,
    selectedItemName: String,
    onSelectionItemChanged: (String) -> Unit,
    onSelectionChanged: (TourItem) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .clickable {
                onSelectionItemChanged(item.name)
                onSelectionChanged(item)
            }.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        RadioButton(
            selected = item.name == selectedItemName,
            onClick = {
                onSelectionChanged(item)
                onSelectionItemChanged(item.name)
            })
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.description
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = item.name)
    }
}


@Preview
@Composable
fun BaseMenuPreview() {
    BaseMenuScreen(
        options = DataSource.categoryItems,
        onPreviousButtonClick = { /*TODO*/ },
        onNextButtonClick = { /*TODO*/ },
        onSelectionChanged = { /*TODO*/ },
        windowSize = WindowWidthSizeClass.Compact
    )
}