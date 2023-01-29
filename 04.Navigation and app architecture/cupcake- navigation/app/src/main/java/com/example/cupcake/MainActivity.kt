/*
 * 2023년 1월 29일
 * Android-Developers
 * 단원 4: Navigation 연습(Navigation Compose를 활용하여 화면 전환
 */
package com.example.cupcake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cupcake.ui.theme.CupcakeTheme

/**
 * Activity for cupcake order flow.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeTheme {
                CupcakeApp()
            }
        }
    }
}
