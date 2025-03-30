package com.example.canvas.path

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PathEffect(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10000f,
        animationSpec = infiniteRepeatable(
            animation = tween(60000, easing = LinearEasing)
        )
    )

    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(100f, 100f)
            cubicTo(
                100f, 300f,
                600f, 700f,
                600f, 1100f
            )
            lineTo(800f, 800f)
            lineTo(1000f, 1100f)
        }

        val oval = Path().apply {
            addOval(Rect(topLeft = Offset.Zero, bottomRight = Offset(40f, 10f)))
        }
        drawPath(
            path = path,
            color = Color.Red,
            style = Stroke(
                width = 5.dp.toPx(),
                //dashPathEffect 점선 그리기 (첫 배열은 길이, 두 번째는 빈값)
//                pathEffect = PathEffect.dashPathEffect(
//                    intervals = floatArrayOf(50f, 30f),
//                    phase = phase
//                )

                //corner
                //코너 속성을 지정할 수 있다.
//                pathEffect = PathEffect.cornerPathEffect(
//                    radius = 1000f
//                )

                //stampedPathEffect
                //점선의 모양을 지정할 수 있으며, advance로 간격 조잘 가능
//                pathEffect = PathEffect.stampedPathEffect(
//                    shape = oval,
//                    advance = 200f,
//                    phase = phase,
//                    style = StampedPathEffectStyle.Rotate
//                )

                //chainPathEffect
                //2개의 PathEffect 지정
                pathEffect = PathEffect.chainPathEffect(
                    outer = PathEffect.stampedPathEffect(
                        shape = oval,
                        advance = 30f,
                        phase = 0f,
                        style = StampedPathEffectStyle.Rotate
                    ),
                    inner = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(100f, 100f)
                    )
                )

            )
        )
    }
}