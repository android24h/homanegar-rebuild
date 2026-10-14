package com.alirezahalvaei.homanegar.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alirezahalvaei.homanegar.core.locale.LocalStrings

import com.alirezahalvaei.homanegar.presentation.theme.LocalBrand
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val strings = LocalStrings.current
    val brand = LocalBrand.current

    val frameProgress = remember { Animatable(0f) }
    val chartProgress = remember { Animatable(0f) }
    val dotScale = remember { Animatable(0f) }
    val exit = remember { Animatable(0f) }
    var showTitle by remember { mutableStateOf(false) }
    var showTagline by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        launch { frameProgress.animateTo(1f, tween(1000, easing = FastOutSlowInEasing)) }
        delay(650)
        launch { chartProgress.animateTo(1f, tween(700, easing = FastOutSlowInEasing)) }
        delay(550)
        launch { dotScale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy)) }
        showTitle = true
        delay(350)
        showTagline = true
        delay(1200)
        exit.animateTo(1f, tween(380))
        onFinished()
    }

    val infinite = rememberInfiniteTransition(label = "splash")

    val time by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(9000, easing = LinearEasing)),
        label = "time"
    )
    val pulse by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2200, easing = LinearEasing)),
        label = "pulse"
    )
    val glow by infinite.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            tween(1400, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ),
        label = "glow"
    )

    val deep = Color(0xFF070A1A)
    val primary = brand.primary
    val secondary = brand.secondary

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(deep, lerp(deep, primary, 0.45f), lerp(deep, secondary, 0.35f))
                )
            )
            .graphicsLayer {
                alpha = 1f - exit.value
                scaleX = 1f + exit.value * 0.08f
                scaleY = 1f + exit.value * 0.08f
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.fillMaxSize()) {
            drawAurora(time, primary, secondary, brand.tertiary)
            drawParticles(time)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(220.dp)) {
                // موج‌های منتشرشونده
                Canvas(Modifier.fillMaxSize()) {
                    repeat(2) { i ->
                        val p = (pulse + i * 0.5f) % 1f
                        drawCircle(
                            color = Color.White.copy(alpha = (1f - p) * 0.18f * frameProgress.value),
                            radius = size.minDimension * (0.28f + p * 0.22f),
                            style = Stroke(width = 2.dp.toPx())
                        )
                    }
                    drawCircle(
                        brush = Brush.radialGradient(
                            listOf(primary.copy(alpha = glow * 0.55f), Color.Transparent)
                        ),
                        radius = size.minDimension * 0.42f
                    )
                }
                HomaNegarLogo(
                    frame = frameProgress.value,
                    chart = chartProgress.value,
                    dot = dotScale.value,
                    accent = brand.tertiary,
                    modifier = Modifier.size(118.dp)
                )
            }

            Spacer(Modifier.height(8.dp))

            AnimatedVisibility(
                visible = showTitle,
                enter = fadeIn(tween(600)) + slideInVertically(
                    tween(
                        600,
                        easing = FastOutSlowInEasing
                    )
                ) { it / 2 }
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = strings.appName,
                        color = Color.White,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Black
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

            AnimatedVisibility(visible = showTagline, enter = fadeIn(tween(700))) {
                Text(
                    text = strings.tagline,
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

/**
 * لوگوی همانگار: قاب گرد (نماد کسب‌وکار) و نمودار رو به رشد که به‌تدریج ترسیم می‌شوند.
 */
@Composable
private fun HomaNegarLogo(
    frame: Float,
    chart: Float,
    dot: Float,
    accent: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier) {
        val w = size.width
        val stroke = w * 0.075f

        // پس‌زمینه‌ی شیشه‌ای
        drawRoundRect(
            color = Color.White.copy(alpha = 0.08f * frame),
            topLeft = Offset(w * 0.1f, w * 0.1f),
            size = androidx.compose.ui.geometry.Size(w * 0.8f, w * 0.8f),
            cornerRadius = CornerRadius(w * 0.24f)
        )

        // قاب
        val framePath = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(w * 0.1f, w * 0.1f, w * 0.9f, w * 0.9f),
                    cornerRadius = CornerRadius(w * 0.24f)
                )
            )
        }
        drawTrimmed(
            path = framePath,
            progress = frame,
            brush = Brush.linearGradient(listOf(Color.White, Color.White.copy(alpha = 0.55f))),
            width = stroke
        )

        // نمودار رو به رشد
        val chartPath = Path().apply {
            moveTo(w * 0.28f, w * 0.64f)
            lineTo(w * 0.43f, w * 0.49f)
            lineTo(w * 0.54f, w * 0.59f)
            lineTo(w * 0.72f, w * 0.38f)
        }
        drawTrimmed(
            path = chartPath,
            progress = chart,
            brush = Brush.linearGradient(listOf(Color.White, accent)),
            width = stroke
        )

        // نقطه‌ی پایانی
        if (dot > 0f) {
            drawCircle(
                color = accent.copy(alpha = 0.35f),
                radius = w * 0.1f * dot,
                center = Offset(w * 0.72f, w * 0.38f)
            )
            drawCircle(
                color = Color.White,
                radius = w * 0.055f * dot,
                center = Offset(w * 0.72f, w * 0.38f)
            )
        }
    }
}

private fun DrawScope.drawTrimmed(path: Path, progress: Float, brush: Brush, width: Float) {
    if (progress <= 0f) return
    val measure = PathMeasure()
    measure.setPath(path, false)
    val segment = Path()
    measure.getSegment(0f, measure.length * progress, segment, true)
    drawPath(
        path = segment,
        brush = brush,
        style = Stroke(width = width, cap = StrokeCap.Round, join = StrokeJoin.Round)
    )
}

// هاله‌های رنگی متحرک و ذرات شناور
private fun DrawScope.drawAurora(time: Float, a: Color, b: Color, c: Color) {
    val angle = time * 2 * PI
    val blobs = listOf(
        Triple(a, 0.2f, 0.0),
        Triple(b, 0.8f, 2.1),
        Triple(c, 0.5f, 4.2)
    )
    blobs.forEach { (color, baseX, phase) ->
        val cx = size.width * (baseX + 0.12f * cos(angle + phase).toFloat())
        val cy = size.height * (0.35f + 0.18f * sin(angle * 1.3 + phase).toFloat())
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(color.copy(alpha = 0.32f), Color.Transparent),
                center = Offset(cx, cy),
                radius = size.minDimension * 0.7f
            ),
            radius = size.minDimension * 0.7f,
            center = Offset(cx, cy)
        )
    }
}

private val particleSeeds = List(22) { i ->
    val x = ((i * 73 + 17) % 100) / 100f
    val y = ((i * 41 + 29) % 100) / 100f
    val speed = 0.35f + ((i * 13) % 10) / 14f
    val radius = 1.2f + (i % 4) * 0.7f
    floatArrayOf(x, y, speed, radius)
}

private fun DrawScope.drawParticles(time: Float) {
    particleSeeds.forEach { (x, y, speed, radius) ->
        val py = ((y - time * speed) % 1f + 1f) % 1f
        val fade = sin(py * PI).toFloat()
        drawCircle(
            color = Color.White.copy(alpha = 0.35f * fade),
            radius = radius * density,
            center = Offset(size.width * x, size.height * py)
        )
    }
}

