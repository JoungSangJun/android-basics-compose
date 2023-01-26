package com.example.android.unscramble.ui

/*
 * UiState 로 사용자에게 UI로 보여줘야 할 항목
 */
data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 0,
    val isGameOver: Boolean = false
)