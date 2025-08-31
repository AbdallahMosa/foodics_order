package com.foodics.foodicsorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.foodics.presentation.theme.FoodicsOrderTheme
import com.foodics.presentation.MenuScreenRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                MenuScreenRoute()

        }
    }
}

