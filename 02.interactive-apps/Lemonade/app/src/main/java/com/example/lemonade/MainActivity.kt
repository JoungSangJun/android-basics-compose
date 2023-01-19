/*
 * 2022년 1월 15일
 * Android-Developers
 * 단원 2: 앱 UI 빌드
 * 앱에 버튼 추가(Lemon Tree)
 * 이미지 클릭 이벤트 및 Stateless 변수 Compose 사용하여 해결
 */

package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    // 현재 단계는 앱의 화면을 출력
    // 1-4 로 앱이 출력 할 이미지 결정
    var currentStep by remember {
        mutableStateOf(1)
    }
    // 레몬을 레몬즙으로 만들기 위해 탭 해야하는 횟수
    var squeezeCount by remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            // 레몬 나무 출력, 사용자에게 나무에서 레몬을 따라고 요청
            1 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.first_stage_user_description,
                    drawableResourceId = R.drawable.lemon_tree,
                    contentDescriptionResourceId = R.string.lemon_tree_content_description,
                    onImageClick = {
                        // 다음 화면 출력
                        currentStep = 2
                        // 나무에서 레몬을 따고
                        // 레몬을 즙으로 만들기
                        // 위해 탭을 해야하는 횟수를 squeezeCount에
                        // 랜덤으로 2-4 사이에 값을 받는다
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.second_stage_user_description,
                    drawableResourceId = R.drawable.lemon_squeeze,
                    contentDescriptionResourceId = R.string.lemon_content_description,
                    onImageClick = {
                        // 유저가 이미지를 탭하면 squeezeCount 1 감소
                        squeezeCount--
                        // squeezeCount만큼 클릭하면 다음화면으로 이동
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.third_stage_user_description,
                    drawableResourceId = R.drawable.lemon_drink,
                    contentDescriptionResourceId = R.string.glass_of_lemonade_content_description,
                    onImageClick = {
                        // 다음 단계로 이동
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    textLabelResourceId = R.string.fourth_stage_user_description,
                    drawableResourceId = R.drawable.lemon_restart,
                    contentDescriptionResourceId = R.string.empty_glass_content_description,
                    onImageClick = {
                        // 처음 단계로 이동
                        currentStep = 1
                    }
                )
            }
        }
    }
}

/*
 * 클릭 가능한 이미지와 그 위에 텍스트를 출력
 *
 * @param textLabelResourceId : text String을 출력하기 위한 ResourceId
 * @param drawableResourceId : 텍스트 아래 Image drawable 위한 ResourceId
 * @param contentDescriptionResourceId : Image description 에서 사용할
 * text String ResourceId
 * @param onImageClick : 유저가 이미지를 클릭하면 발생할 이벤트
 * @param modifier : composable 을 디자인
 */

@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = textLabelResourceId),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = drawableResourceId), contentDescription = stringResource(
                id = contentDescriptionResourceId),
                modifier = modifier
                    .clickable ( onClick = onImageClick )
                    .wrapContentSize()
                    .border(
                        BorderStroke(
                            2.dp,
                            Color(105, 205, 216)
                        ), shape = RoundedCornerShape(4.dp)
                    )
                    .padding(16.dp)
            )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}