/*
 * 2023년 1월 26일
 * Android-Developers
 * 단원 4: 아키텍처 연습(Compose의 ViewModel 및 상태)
 * Shuffle 된 단어 사용자가 맞추기 게임
 */

package com.example.android.unscramble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.android.unscramble.ui.GameScreen
import com.example.android.unscramble.ui.theme.UnscrambleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UnscrambleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    GameScreen()
                }
            }
        }
    }
}

