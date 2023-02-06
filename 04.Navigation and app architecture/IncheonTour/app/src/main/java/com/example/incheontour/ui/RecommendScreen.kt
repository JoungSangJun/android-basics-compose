package com.example.incheontour.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.incheontour.data.TourItem

/*
 * Recommend화면 출력
 */

@Composable
fun RecommendScreen(
    options: List<TourItem>,
    onPreviousButtonClick: () -> Unit = {},
    onNextButtonClick: () -> Unit = {},
    onSelectionChanged: (TourItem) -> Unit,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onPreviousButtonClick = onPreviousButtonClick,
        onNextButtonClick = onNextButtonClick,
        onSelectionChanged = onSelectionChanged,
        windowSize = windowSize,
        modifier = modifier
    )
}