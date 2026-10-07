package com.alirezahalvaei.homanegar.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alirezahalvaei.homanegar.core.local.LocalStrings
import com.alirezahalvaei.homanegar.presentation.theme.LocalBrand
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {

    var showTitle by remember { mutableStateOf(false) }
    var showTagLine by remember { mutableStateOf(false) }
    val exit= remember { Animatable(0f) }

    val strings = LocalStrings.current
    val brand = LocalBrand.current

    val deep = Color(0xFF070A1A)
    val primary = brand.primary
    val secondary = brand.secondary

    LaunchedEffect(Unit) {
        delay(1500)
        showTitle = true
        delay(350)
        showTagLine = true
        delay(1800)
        exit.animateTo(1f,tween(600))
        onFinished()
    }
    val infinite=rememberInfiniteTransition(label = "splash")

    val pulse by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2200, easing = LinearEasing)),
        label = "pulse"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(deep, lerp(deep, primary, 0.45f), lerp(deep, secondary, 0.45f))
                )
            )
            .graphicsLayer{
                alpha=1f-exit.value
                scaleX=1f+exit.value*0.08f
                scaleY=1f+exit.value*0.08f
            }

        ,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedVisibility(
                visible = showTitle,
                enter = fadeIn(tween(600))+ slideInVertically(tween(600, easing = FastOutSlowInEasing)){it/2}
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
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


                }
            }
            Spacer(Modifier.height(14.dp))


            AnimatedVisibility(
                visible = showTagLine,
                enter = fadeIn(tween(700))
            ) {
                Text(
                    text = strings.tagLine,
                    color = Color.White.copy(alpha = 0.72f),
                    fontSize = 14.sp
                )
            }

        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingDots(pulse)
            Spacer(Modifier.height(10.dp))
            Text(
                text = strings.loading,
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 12.sp
            )
        }


    }
}

@Composable
private fun LoadingDots(phase: Float) {
    Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
        repeat(3) { i ->
            val local = ((phase * 3f - i) % 3f + 3f) % 3f
            val lift = if (local < 1f) sin(local * PI).toFloat() else 0f
            Box(
                Modifier
                    .size(8.dp)
                    .graphicsLayer {
                        translationY = -lift * 8.dp.toPx()
                        alpha = 0.4f + lift * 0.6f
                    }
                    .background(Color.White, CircleShape)
            )
        }
    }
}