package com.example.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.canvas.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

//                    MyCanvas(modifier = Modifier.padding(innerPadding))
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .drawBehind {
//                                //위와 같이 Box에서도 draw를 할 수 있다.
//                            }
//                            .padding(innerPadding)
//                    )
                }
            }
        }
    }
}

@Composable
fun MyCanvas(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .padding(20.dp)
            .size(300.dp)
    ) {
        //해당 캔버스의 가운데 부분의 위치, Offset 객체
        //center

        //캔버스의 사이즈, Size 객체
        //size

        //직사각형 그리기, 캔버스 사이즈만큼 검은색으로 채운 사각형이 생김
        drawRect(
            color = Color.Black,
            size = size
        )

        drawRect(
            color = Color.Red,
            topLeft = Offset(100f, 100f),
            size = Size(100f, 100f),
            style = Stroke(
                width = 3.dp.toPx(),
            )
        )

        //원을 그리는 객체
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Red, Color.Yellow),
                center = center,
                radius = 100f
            ),
            radius = 100f
        )

        //아크 그르기
        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = false,
            topLeft = Offset(100f, 500f),
            size = Size(200f, 200f),
            style = Stroke(
                width = 3.dp.toPx()
            )
        )

        //타원 그리기
        drawOval(
            color = Color.Magenta,
            topLeft = Offset(500f, 100f),
            size = Size(200f, 300f)
        )

        //선
        drawLine(
            color = Color.Cyan,
            start = Offset(300f, 700f),
            end = Offset(700f, 700f),
            strokeWidth = 5.dp.toPx()
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CanvasTheme {
        Greeting("Android")
    }
}