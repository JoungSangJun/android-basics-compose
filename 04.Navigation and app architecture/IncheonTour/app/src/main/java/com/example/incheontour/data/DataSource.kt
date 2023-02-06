package com.example.incheontour.data

import android.media.Image
import com.example.incheontour.R

/*
 * Category, Recommend 창에서 사용할
 * Data format과 Data저장
 */

sealed class TourItem(
    open val imageRes: Int,
    open val description: String,
    open val name: String,
) {

    data class CategoryItem(
        override val imageRes: Int,
        override val description: String,
        override val name: String,
    ) : TourItem(imageRes, description, name)

    data class RecommendCafeItem(
        override val imageRes: Int,
        override val description: String,
        override val name: String,
    ) : TourItem(imageRes, description, name)

    data class RecommendRestaurantItem(
        override val imageRes: Int,
        override val description: String,
        override val name: String,
    ) : TourItem(imageRes, description, name)

    data class RecommendShoppingItem(
        override val imageRes: Int,
        override val description: String,
        override val name: String,
    ) : TourItem(imageRes, description, name)

    data class RecommendParkItem(
        override val imageRes: Int,
        override val description: String,
        override val name: String,
    ) : TourItem(imageRes, description, name)
}

object DataSource {

    val categoryItems = listOf(
        TourItem.CategoryItem(
            imageRes = R.drawable.baseline_coffee_24,
            description = "Coffee Icon",
            name = "커피숍"
        ),
        TourItem.CategoryItem(
            imageRes = R.drawable.baseline_restaurant_24,
            description = "Restaurant Icon",
            name = "음식점"
        ),
        TourItem.CategoryItem(
            imageRes = R.drawable.baseline_shopping_cart_24,
            description = "Shopping Icon",
            name = "쇼핑몰"
        ),
        TourItem.CategoryItem(
            imageRes = R.drawable.baseline_park_24,
            description = "Park Icon",
            name = "공원"
        )
    )

    val recommendCafeItem = listOf(
        TourItem.RecommendCafeItem(
            imageRes = R.drawable.baseline_coffee_24,
            description = "조양방직",
            name = "조양방직"
        ),
        TourItem.RecommendCafeItem(
            imageRes = R.drawable.baseline_coffee_24,
            description = "포레스트아웃팅스",
            name = "포레스트아웃팅스"
        ),
        TourItem.RecommendCafeItem(
            imageRes = R.drawable.baseline_coffee_24,
            description = "카페오라",
            name = "카페오라"
        )
    )

    val recommendRestaurantItem = listOf(
        TourItem.RecommendRestaurantItem(
            imageRes = R.drawable.baseline_restaurant_24,
            description = "나운순대",
            name = "나운순대"
        ),
        TourItem.RecommendRestaurantItem(
            imageRes = R.drawable.baseline_restaurant_24,
            description = "남동공단떡볶이",
            name = "남동공단떡볶이"
        ),
        TourItem.RecommendRestaurantItem(
            imageRes = R.drawable.baseline_restaurant_24,
            description = "연경",
            name = "연경"
        )
    )

    val recommendShoppingItem = listOf(
        TourItem.RecommendShoppingItem(
            imageRes = R.drawable.baseline_shopping_cart_24,
            description = "롯데백화점",
            name = "롯데백화점"
        ),
        TourItem.RecommendShoppingItem(
            imageRes = R.drawable.baseline_shopping_cart_24,
            description = "데이드리밍 스토어",
            name = "데이드리밍 스토어"
        ),
        TourItem.RecommendShoppingItem(
            imageRes = R.drawable.baseline_shopping_cart_24,
            description = "스퀘어원",
            name = "스퀘어원"
        )
    )

    val recommendParkItem = listOf(
        TourItem.RecommendParkItem(
            imageRes = R.drawable.baseline_park_24,
            description = "만석화수 해안",
            name = "만석화수 해안"
        ),
        TourItem.RecommendParkItem(
            imageRes = R.drawable.baseline_park_24,
            description = "서포리웰빙 삼림욕",
            name = "서포리웰빙 삼림욕"
        ),
        TourItem.RecommendParkItem(
            imageRes = R.drawable.baseline_park_24,
            description = "강화나들길2코스",
            name = "강화나들길2코스"
        )
    )
}

sealed class TourUiStateItem(
    open val image: Int? = null,
    open val address: String? = null,
    open val businessHours: String? = null,
    open val tel: String? = null,
) {
    data class Cafe(
        override val image: Int? = null,
        override val address: String? = null,
        override val businessHours: String? = null,
        override val tel: String? = null,
    ) : TourUiStateItem()


    data class Restaurant(
        override val image: Int? = null,
        override val address: String? = null,
        override val businessHours: String? = null,
        override val tel: String? = null,
    ) : TourUiStateItem()

    data class Shopping(
        override val image: Int? = null,
        override val address: String? = null,
        override val businessHours: String? = null,
        override val tel: String? = null,
    ) : TourUiStateItem()

    data class Trail(
        override val image: Int? = null,
        override val address: String? = null,
        override val businessHours: String? = null,
        override val tel: String? = null,
    ) : TourUiStateItem()

}

object TourUiStateList {
    val cafe = listOf(
        TourUiStateItem.Cafe(
            image = R.drawable.cafe1,
            address = "인천 강화군 강화읍 향나무길5번길 12",
            businessHours = "11 : 00 - 20 : 00",
            tel = "0507-1307-2192"
        ),
        TourUiStateItem.Cafe(
            image = R.drawable.cafe2,
            address = "인천 연수구 청량로 145",
            businessHours = "10 : 00 - 22 : 00",
            tel = "0507-1377-3750"
        ),
        TourUiStateItem.Cafe(
            image = R.drawable.cafe3,
            address = "인천 중구 용유서로 380",
            businessHours = "10 : 00.  22 : 30",
            tel = "032-752-0888"
        )
    )
    val restaurant = listOf(
        TourUiStateItem.Restaurant(
            image = R.drawable.restaurant1,
            address = "인천 남동구 소래역로46번길 28 대영로데오 101호",
            businessHours = "10 : 00 - 22 : 00",
            tel = "032-438-5070"
        ), TourUiStateItem.Restaurant(
            image = R.drawable.restaurant2,
            address = "인천 남동구 남동서로 226",
            businessHours = "07 : 00 - 19:00",
            tel = "032-821-5566"
        ), TourUiStateItem.Restaurant(
            image = R.drawable.restaurant3,
            address = "인천 중구 차이나타운로 41",
            businessHours = "10 : 30 - 21 : 30",
            tel = "0507-1389-7894"
        )
    )
    val shopping = listOf(
        TourUiStateItem.Shopping(
            image = R.drawable.shopping1,
            address = "인천 미추홀구 연남로 35",
            businessHours = "10 : 30 - 20 : 00",
            tel = "1577-0001"
        ), TourUiStateItem.Shopping(
            image = R.drawable.shopping2,
            address = "인천 남동구 남동대로711번길 30",
            businessHours = "13 : 00 - 20 : 00",
            tel = "0507-1375-6484"
        ), TourUiStateItem.Shopping(
            image = R.drawable.shopping3,
            address = "인천 연수구 청능대로 210",
            businessHours = "10 : 30 - 22 : 00",
            tel = "032-456-4154"
        )
    )
    val trail = listOf(
        TourUiStateItem.Trail(
            image = R.drawable.trail1,
            address = "인천 동구 만석동 2-289",
            businessHours = "00 : 00 - 24 : 00",
            tel = "X"
        ),
        TourUiStateItem.Trail(
            image = R.drawable.trail2,
            address = "인천 옹진군 덕적면 덕적남로606번길",
            businessHours = "00 : 00 - 24 : 00",
            tel = "X"
        ),
        TourUiStateItem.Trail(
            image = R.drawable.trail3,
            address = "인천 강화군 불은면 오두리 698",
            businessHours = "00 : 00 - 24 : 00",
            tel = "X"
        ),
    )
}