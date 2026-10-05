package com.alirezahalvaei.homanegar.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.alirezahalvaei.homanegar.core.local.LocalStrings
import com.alirezahalvaei.homanegar.domain.business.BusinessCatalog
import com.alirezahalvaei.homanegar.presentation.theme.LocalBrand
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinished:()-> Unit
){

    val strings= LocalStrings.current
    val brand = LocalBrand.current

    val deep=Color(0xFF070A1A)
    val primary=brand.primary
    val secondary=brand.secondary

    LaunchedEffect(Unit) {
        delay(3000)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(deep, lerp(deep,primary,0.45f), lerp(deep,secondary,0.45f))
                )
            ),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = strings.appName,
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "H O M A N E G A R",
                color = Color.White.copy(alpha = 0.45f),
                fontSize = 11.sp,
                letterSpacing = 3.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(14.dp))
            Text(
                text = strings.tagLine,
                color = Color.White.copy(alpha = 0.72f),
                fontSize = 14.sp
            )
        }

        Text(
            text = strings.loading,
            color = Color.White.copy(alpha = 0.5f),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 36.dp)
        )

    }
}