package com.example.incheontour.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 * 시작화면 출력
 * Start버튼 누르면 CategoryScreen으로 화면 전환
 */

@Composable
fun StartScreen(onStartButtonClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Incheon", style = MaterialTheme.typography.h1)
        Text(text = "명소 소개", style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = onStartButtonClick, modifier = Modifier.size(300.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)
        ) {
            Text(text = "Start", fontSize = 24.sp)
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(onStartButtonClick = { })
}