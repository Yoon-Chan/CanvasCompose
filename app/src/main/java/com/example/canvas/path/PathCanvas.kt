package com.example.canvas.path

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun PathCanvas(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .fillMaxSize()
    ) {
        val path = Path().apply {
            moveTo(1000f, 100f)
            lineTo(100f, 500f)
            lineTo(500f, 500f)
//            lineTo(500f, 100f)
            //lineTo(100f, 100f)

//            quadraticBezierTo(
//                800f, 300f,
//                500f, 100f
//            )

            cubicTo(
                //처음 끌어당길 위치
                800f, 500f,
                //두 번째 끌어당길 위치
                800f, 100f,
                //종료 위치
                500f, 100f
            )
//            close()
        }
        drawPath(
            path,
            color = Color.Red,
            style = Stroke(
                width = 5.dp.toPx(),
                //끝부분 처리
                //Square는 끝부분에 네모 추가, Butt 자르기(기본값), Round는 원처리
                cap = StrokeCap.Round,
                //join은 꼭지점 처리기능
                //Round는 원처리, Bevel을 대각 자르기, Miter는 각진처리(기본값)
                join = StrokeJoin.Miter,
                //Miter 관련 각진 처리 범위 지정인듯
                miter = 10f
            )
        )
    }

}