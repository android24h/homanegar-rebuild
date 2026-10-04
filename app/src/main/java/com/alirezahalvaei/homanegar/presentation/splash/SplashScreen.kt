package com.alirezahalvaei.homanegar.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF4F46E5)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "همانگار",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

    }
}