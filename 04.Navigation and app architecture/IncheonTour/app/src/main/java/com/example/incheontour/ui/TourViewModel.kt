package com.example.incheontour.ui

import androidx.lifecycle.ViewModel
import com.example.incheontour.data.TourItem
import com.example.incheontour.data.TourUiStateList
import com.example.incheontour.model.TourUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TourViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TourUiState())
    val uiState: StateFlow<TourUiState> = _uiState.asStateFlow()


    /**
     * RadioButton 클릭시 TourUiState.category Radio 이름으로 변경
     */
    fun updateCategory(tourItem: TourItem) {
        _uiState.update { currentState ->
            currentState.copy(
                category = tourItem.name
            )
        }
    }

    /**
     * RadioButton 클릭시 Category와 Recommend를 사용해
     * TourUiState의 값 입력
     */
    fun updateTourUiState(tourItem: TourItem) {

        val category = when (_uiState.value.category) {
            "커피숍" -> "cafe"
            "음식점" -> "restaurant"
            "쇼핑몰" -> "shopping"
            else -> "trail"
        }

        val recommend = when (tourItem.name) {
            "조양방직", "나운순대", "롯데백화점", "만석화수 해안" -> 0
            "포레스트아웃팅스", "남동공단떡볶이", "데이드리밍 스토어", "서포리웰빙 삼림욕" -> 1
            else -> 2
        }

        when (category) {
            "cafe" -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        recommend = tourItem.name,
                        tel = TourUiStateList.cafe[recommend].tel,
                        image = TourUiStateList.cafe[recommend].image,
                        address = TourUiStateList.cafe[recommend].address,
                        businessHours = TourUiStateList.cafe[recommend].businessHours
                    )
                }
            }
            "restaurant" -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        recommend = tourItem.name,
                        tel = TourUiStateList.restaurant[recommend].tel,
                        image = TourUiStateList.restaurant[recommend].image,
                        address = TourUiStateList.restaurant[recommend].address,
                        businessHours = TourUiStateList.restaurant[recommend].businessHours
                    )
                }
            }
            "shopping" -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        recommend = tourItem.name,
                        tel = TourUiStateList.shopping[recommend].tel,
                        image = TourUiStateList.shopping[recommend].image,
                        address = TourUiStateList.shopping[recommend].address,
                        businessHours = TourUiStateList.shopping[recommend].businessHours
                    )
                }
            }
            else -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        recommend = tourItem.name,
                        tel = TourUiStateList.trail[recommend].tel,
                        image = TourUiStateList.trail[recommend].image,
                        address = TourUiStateList.trail[recommend].address,
                        businessHours = TourUiStateList.trail[recommend].businessHours
                    )
                }
            }
        }


    }
}