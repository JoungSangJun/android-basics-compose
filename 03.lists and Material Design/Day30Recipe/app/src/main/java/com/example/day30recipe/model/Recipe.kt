package com.example.day30recipe.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.day30recipe.R

data class Recipe(
    @DrawableRes val cookingImgRes : Int,
    @StringRes val day : Int,
    @StringRes val name : Int,
    @StringRes val ingredients : Int
)

val cooking  = listOf(
    Recipe(R.drawable.cooking1, R.string.day1, R.string.day1_cooking, R.string.day1_cooking_ingredients),
    Recipe(R.drawable.cooking2, R.string.day2, R.string.day2_cooking, R.string.day2_cooking_ingredients),
    Recipe(R.drawable.cooking3, R.string.day3, R.string.day3_cooking, R.string.day3_cooking_ingredients),
    Recipe(R.drawable.cooking4, R.string.day4, R.string.day4_cooking, R.string.day4_cooking_ingredients),
    Recipe(R.drawable.cooking5, R.string.day5, R.string.day5_cooking, R.string.day5_cooking_ingredients)
)

