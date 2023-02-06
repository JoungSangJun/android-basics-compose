package com.example.incheontour

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.incheontour.data.DataSource
import com.example.incheontour.ui.*
import com.example.incheontour.ui.utils.TourContentType

/*
 * Navigate를 사용하여 앱의 화면 이동 클래스
 */

enum class IncheonTourScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Category(title = R.string.choose_category),
    Recommend(title = R.string.choose_recommend),
    PlaceInfo(title = R.string.place_info),
}


/*
 * AppBar 구현
 */
@Composable
fun IncheonTourAppBar(
    currentScreen: IncheonTourScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        modifier = modifier.padding(bottom = 30.dp),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

/**
 * 앱바 및 컨텐츠 출력
 *
 * @param windowSize : 사용자 휴대폰 사이즈 크기 매개변수
 */
@Composable
fun IncheonTourApp(windowSize: WindowWidthSizeClass, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val currentScreen = IncheonTourScreen.valueOf(
        backStack?.destination?.route ?: IncheonTourScreen.Start.name
    )

    val viewModel: TourViewModel = viewModel()


    // 핸드폰 화면 크기별로 나누기
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> {
            TourContentType.DIVIDE_CONTENTS
        }
        WindowWidthSizeClass.Expanded -> {
            TourContentType.ALL_CONTENTS
        }
        else -> {
            TourContentType.DIVIDE_CONTENTS
        }
    }

    Scaffold(
        topBar = {
            IncheonTourAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = IncheonTourScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = IncheonTourScreen.Start.name) {
                StartScreen(onStartButtonClick = {
                    // 화면이 Expended이면 AllContentsScreen 실행
                    if (contentType == TourContentType.DIVIDE_CONTENTS) {
                        navController.navigate(
                            IncheonTourScreen.Category.name
                        )
                    }
                })
            }


            composable(route = IncheonTourScreen.Category.name) {
                CategoryScreen(
                    options = DataSource.categoryItems,
                    onPreviousButtonClick = {
                        navController.popBackStack()
                    },
                    onNextButtonClick = {
                        navController.navigate(
                            IncheonTourScreen.Recommend.name
                        )
                    },
                    onSelectionChanged = { viewModel.updateCategory(it) },
                    windowSize = windowSize
                )
            }

            composable(route = IncheonTourScreen.Recommend.name) {
                val option = when (uiState.category) {
                    "커피숍" -> DataSource.recommendCafeItem
                    "음식점" -> DataSource.recommendRestaurantItem
                    "쇼핑몰" -> DataSource.recommendShoppingItem
                    else -> DataSource.recommendParkItem
                }
                RecommendScreen(
                    options = option,
                    onPreviousButtonClick = {
                        navController.popBackStack()
                    },
                    onNextButtonClick = {
                        navController.navigate(
                            IncheonTourScreen.PlaceInfo.name
                        )
                    },
                    onSelectionChanged = { viewModel.updateTourUiState(it) },
                    windowSize = windowSize
                )
            }

            composable(route = IncheonTourScreen.PlaceInfo.name) {
                PlaceInfoScreen(
                    windowSize = windowSize,
                    onPreviousButtonClick = { navController.popBackStack() },
                    onCancelButtonClick = {
                        navController.popBackStack(
                            IncheonTourScreen.Start.name,
                            inclusive = false
                        )
                    },
                    tourUiState = uiState
                )
            }
        }
    }
}

