package com.example.canvas.path

import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun PathText(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(200f, 800f)
            quadTo(400f, 400f, 1000f, 800f)
        }
        drawContext.canvas.nativeCanvas.apply {
            //public void drawTextOnPath(
            //  String text,
            //  Path path,
            //  float hOffset,
            //  float vOffset,
            //  Paint paint
            // )
            drawPath(path, Paint().apply {
                color = android.graphics.Color.BLUE
            })

            drawTextOnPath(
                "Hello World!",
                path,
                5f,
                50f,
                Paint().apply {
                    color = android.graphics.Color.RED
                    textSize = 70f
                    textAlign = Paint.Align.CENTER
                }
            )
        }
    }
}