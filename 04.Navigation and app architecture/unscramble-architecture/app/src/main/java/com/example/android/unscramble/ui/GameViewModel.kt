package com.example.android.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
 * Data Layer 와 UI 요소 사이의 징검다리
 * 데이터를 가져와 가공한 후 UI 출력을 위해 Compose 클래스로 데이터 전달
 */

class GameViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())

    var userGuess by mutableStateOf("")
        private set

    // UI state 외부에서 변경 예방 및 접근가능
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private lateinit var currentWord: String

    // 지나간 문자 저장, 다음번에 포함된 단어 나오면 생략
    var usedWold: MutableSet<String> = mutableSetOf()

    init {
        resetGame()
    }

    /**
     * 스킵 버튼 클릭시 TextField 초기화 및 다음 게임위해 셋팅
     */
    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }

    /**
     * Skip or 정답을 맞추면
     * 새로운 단어 맞추기 위해 셋팅
     *
     * @param updateScore : 화면 셋팅할 때 사용할 점수 값
     */
    private fun updateGameState(updateScore: Int) {
        if(_uiState.value.currentWordCount == MAX_NO_OF_WORDS){
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updateScore,
                    currentWordCount = currentState.currentWordCount.inc(),
                    isGameOver = true
                )
            }
        }else{
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updateScore,
                    currentScrambledWord = pickRandomAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                )
            }
        }
    }

    /**
     * 유저의 답과 정답을 비교
     */
    fun checkUserGuess() {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updateScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updateScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = true
                )
            }
        }
        updateUserGuess("")
    }

    /**
     * Data layer에서 임의 단어 가져와
     * 단어 셔플 후 반환
    환  */
    private fun pickRandomAndShuffle(): String {
        currentWord = allWords.random()
        if (usedWold.contains(currentWord)) {
            return pickRandomAndShuffle()
        } else {
            usedWold.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    /**
     * 현재 문자열을 셔플해 셔플된 문자열 반환
     *
     * @param word : 현재 문자
     */
    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        tempWord.shuffle()
        while (tempWord.equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }


    /**
     * 게임 초기화 함수
     * 지금까지 했던 단어 초기화
     * 새로운 단어 셔플
     */
    fun resetGame() {
        usedWold.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomAndShuffle())
    }


    /**
     * 유저가 생각하는 답 입력시 userGuess에 저장
     *
     * @param guessedWord : 유저가 추측하는 값
     */
    fun updateUserGuess(guessedWord: String) {
        userGuess = guessedWord
    }


}