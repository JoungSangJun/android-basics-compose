@file:Suppress("UNUSED_EXPRESSION")

package com.example.day30recipe

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.day30recipe.model.Recipe
import com.example.day30recipe.model.cooking
import com.example.day30recipe.ui.theme.Day30RecipeTheme

/**
 * 2023년 1월 25일
 * 단원 3: Material Theming, Animation, LazyColumn 실습
 * 5일간 요리와 요리에 필요한 재료를 알려주는 App
 *
 * Image 의 modifier = Modifier.clickable(onClick = { expanded = !expanded }) 에서
 * onClick 람다식을 clickable 매개변수로 전달해야 하는데 람다식으로 전달하는 실수를 함
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Day30RecipeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RecipeApp()
                }
            }
        }
    }
}

/**
 * 앱의 앱바를 구현
 */
@Composable
fun RecipeTopBar() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .height(70.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.White,
            fontSize = 35.sp
        )
    }
}

/**
 * 앱바와 cooking 리스트를 화면에 출력
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeApp() {
    Scaffold(topBar = { RecipeTopBar() }) {
        LazyColumn {
            items(cooking) {
                RecipeListItem(recipe = it)
            }
        }
    }

}


/**
 * recipe에 이미지, 이름, 재료 등을 List형태로 출력
 *
 * @param recipe : list item에서 사용할 정보
 * @param modifier : 수정자
 */
@Composable
fun RecipeListItem(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    // 재료 출력 여부 확인
    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp, modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(start = 30.dp, bottom = 10.dp, end = 28.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Text(text = stringResource(id = recipe.day), style = MaterialTheme.typography.h1)
            Text(text = stringResource(recipe.name), style = MaterialTheme.typography.body1)
            Image(
                painter = painterResource(id = recipe.cookingImgRes),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = { expanded = !expanded })
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (expanded) {
                RecipeIngredients(recipe.ingredients)
            }
        }
    }
}

/**
 * 요리에 필요한 재료를 출력
 *
 * @param recipeIngredients : 요리 재료 Resource ID
 * @param modifier : 수정자
 */
@Composable
fun RecipeIngredients(@StringRes recipeIngredients: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(recipeIngredients),
        style = MaterialTheme.typography.body2
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Day30RecipeTheme {
        RecipeListItem(
            Recipe(
                R.drawable.cooking1,
                R.string.day1,
                R.string.day1_cooking,
                R.string.day1_cooking_ingredients
            )
        )
    }
}