package com.example.canvas.path

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2

@Composable
fun AnimatePathArrow(modifier: Modifier = Modifier) {
    val pathPortion = remember {
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = true) {
        pathPortion.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 5000
            )
        )
    }
    val path = Path().apply {
        moveTo(100f,100f)
        quadraticBezierTo(400f, 400f, 100f, 400f)
    }

    val outPath = android.graphics.Path()
    val pos = FloatArray(2)
    val tan = FloatArray(2)
    android.graphics.PathMeasure().apply {
        setPath(path.asAndroidPath(), true)
        getSegment(0f, pathPortion.value * length, outPath, true)
        getPosTan(pathPortion.value * length, pos, tan)
    }

    Canvas(modifier = modifier) {
        drawPath(
            path = outPath.asComposePath(),
            color = Color.Red,
            style = Stroke(
                width = 5.dp.toPx()
            )
        )

        val x = pos[0]
        val y = pos[1]
        val degress = -atan2(tan[0], tan[1]) * (180 / PI.toFloat()) - 180f
        rotate(degress, pivot = Offset(x, y)) {
            drawPath(
                path = Path().apply {
                    moveTo(x, y - 30f)
                    lineTo(x - 30f, y + 60f)
                    lineTo(x + 30f, y + 60f)
                    close()
                },
                color = Color.Red
            )
        }
    }
}