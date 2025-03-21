package com.example.canvas

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb

@Composable
fun DrawingText(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                "This is my Canvas text",
                100f,
                100f,
                Paint().apply {
                    color = Color.Red.toArgb()
                    textSize = 100f
                }
            )
        }
    }
}