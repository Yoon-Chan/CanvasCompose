package com.example.canvas.path

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PathOperations(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val squareWithoutOp = Path().apply {
            addRect(
                Rect(
                    //시작 위치(사각현 왼쪽위 부분지점)
                    Offset(200f, 200f),
                    //크기 지정
                    Size(200f,200f)
                )
            )
        }

        val circle = Path().apply {
            addOval(Rect(Offset(200f,200f), 100f))
        }

        val pathWithOp = Path().apply {
            //op(a1, a2, PathOperation)일 때
            //Intersect 두 모양이 사이에 있는 것
            //Difference a1에서 a2가 포함되지 않는 것. (a1 - Intersect)
            //Xor a1과 a2를 포함하면서 Intersect를 포함하지 않는 것 ((a1+a2) - Intersect)
            //Union a1과 a2를 전부 포함(전체 부분)
            //ReverseDifference a2에서 a1이 포함되지 않는 것. (a2 - Intersect)
            op(squareWithoutOp, circle, PathOperation.Intersect)
        }
        drawPath(
            path = squareWithoutOp,
            color = Color.Red,
            style = Stroke(width = 2.dp.toPx())
        )

        drawPath(
            path = circle,
            color = Color.Blue,
            style = Stroke(width = 2.dp.toPx())
        )

        drawPath(
            path = pathWithOp,
            color = Color.Green,
        )
    }
}