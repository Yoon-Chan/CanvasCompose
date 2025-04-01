package com.example.canvas

import android.icu.text.ListFormatter.Width
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.canvas.clock.Clock
import com.example.canvas.genderpicker.GenderPicker
import com.example.canvas.path.AnimatePathArrow
import com.example.canvas.path.PathAnimation
import com.example.canvas.path.PathCanvas
import com.example.canvas.path.PathEffect
import com.example.canvas.path.PathOperations
import com.example.canvas.path.PathText
import com.example.canvas.path.TransFormationAndClipping
import com.example.canvas.tiktaktok.TikTacToe
import com.example.canvas.ui.theme.CanvasTheme
import com.example.canvas.weigthpicker.Scale
import com.example.canvas.weigthpicker.ScaleStyle
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MainScreen(
//                        modifier = Modifier.padding(innerPadding)
//                    )


//                    MyCanvas(modifier = Modifier.padding(innerPadding))
//                    Box(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .drawBehind {
//                                //위와 같이 Box에서도 draw를 할 수 있다.
//                            }
//                            .padding(innerPadding)
//                    )

                    //DrawingText
//                    DrawingText(modifier = Modifier.padding(innerPadding))

                    //WeightPicker
//                    WeightPicker(innerPadding)

                    //Clock
//                    ClockBox(modifier = Modifier.padding(innerPadding))

                    //PathCanvas
//                    PathCanvas(modifier = Modifier.fillMaxSize().padding(innerPadding))

                    //PathOperation
//                    PathOperations(
//                        modifier = Modifier.fillMaxSize().padding(innerPadding)
//                    )

                    //PathAnimation
//                    PathAnimation(modifier = Modifier.fillMaxSize().padding(innerPadding))

                    //AnimatePathArrow
//                    AnimatePathArrow(modifier = Modifier.fillMaxSize().padding(innerPadding))

                    //TransFormationAndClipping
//                    TransFormationAndClipping(modifier = Modifier.fillMaxSize().padding(innerPadding))

                    //PathEffect
//                    PathEffect(modifier = Modifier.fillMaxSize().padding(innerPadding))

                    //PathText
//                    PathText(modifier = Modifier.fillMaxSize().padding(innerPadding))

                    //GenderPicker
//                    GenderPicker(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding)
//                    ) {
//
//                    }

                    //TicTacToe
                    Box(Modifier
                        .fillMaxSize()
                        .padding(innerPadding)){
                        TikTacToe(
                            modifier = Modifier
                                .align(Alignment.Center),
                            onNewRound = {},
                            onPlayerWin = { player ->

                            }
                        )
                    }
                }

            }
        }
    }

    @Composable
    fun ClockBox(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val milliseconds = remember {
                System.currentTimeMillis()
            }
            var seconds by remember {
                mutableStateOf((milliseconds / 1000f) % 60f)
            }

            var minutes by remember {
                mutableStateOf(((milliseconds / 1000f) / 60) % 60f)
            }

            var hours by remember {
                mutableStateOf((milliseconds / 1000f) / 3600f + 2f)
            }

            LaunchedEffect(key1 = seconds) {
                delay(1000L)
                minutes += 1f / 60f
                hours += 1f / (60f * 60f * 12f)
                seconds += 1f
            }

            Clock(
                seconds = seconds,
                minutes = minutes,
                hours = hours,
            )
        }
    }

    @Composable
    private fun WeightPicker(innerPadding: PaddingValues) {
        var weight by remember {
            mutableStateOf(80)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Select your weight",
                fontSize = 36.sp,
            )

            val text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = 54.sp)) {
                    append("$weight ")
                }

                withStyle(SpanStyle(fontSize = 32.sp, color = Color.Green)) {
                    append("KG")
                }
            }
            Text(
                modifier = Modifier.weight(1f),
                text = text
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
            ) {
                Scale(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .align(Alignment.BottomCenter),
                    style = ScaleStyle(
                        scaleWidth = 200.dp
                    )
                ) {
                    weight = it
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var points by remember {
        mutableStateOf(0)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = "Points : $points",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {
                    isTimerRunning = true
                    points = 0
                }
            ) {
                Text(text = if (isTimerRunning) "Reset" else "Start")
            }

            CountDownTimer(
                isTimerRunning = isTimerRunning
            ) {
                isTimerRunning = false
            }
        }

        BallClicker(
            enabled = isTimerRunning
        ) {
            points++
        }
    }
}

@Composable
fun CountDownTimer(
    modifier: Modifier = Modifier,
    time: Int = 30000,
    isTimerRunning: Boolean = false,
    onTimerEnd: () -> Unit = {}
) {
    var curTime by remember {
        mutableStateOf(time)
    }

    LaunchedEffect(
        key1 = curTime, key2 = isTimerRunning
    ) {
        if (!isTimerRunning) {
            curTime = time
            return@LaunchedEffect
        }

        if (curTime > 0) {
            delay(1000L)
            curTime -= 1000
        } else {
            onTimerEnd()
        }
    }

    Text(
        modifier = modifier,
        text = (curTime / 1000).toString(),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BallClicker(
    modifier: Modifier = Modifier,
    radius: Float = 100f,
    enabled: Boolean = false,
    ballColor: Color = Color.Red,
    onBallClick: () -> Unit = {}
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        var ballPosition by remember {
            mutableStateOf(randomOffset(
                radius = radius,
                width = constraints.maxWidth,
                height = constraints.maxHeight
            ))
        }

        Canvas(
            modifier = modifier
                .fillMaxSize()
                .pointerInput(enabled) {
                    if (!enabled) {
                        return@pointerInput
                    }

                    detectTapGestures {
                        //클릭한 포지션의 지점과 원의 지점간의 거리 구하기
                        val distance = sqrt(
                            (it.x - ballPosition.x).pow(2) +
                                    (it.y - ballPosition.y).pow(2)
                        )

                        if (distance < radius) {
                            ballPosition = randomOffset(
                                radius = radius,
                                width = constraints.maxWidth,
                                height = constraints.maxHeight
                            )
                            onBallClick()
                        }
                    }
                }
        ) {
            drawCircle(
                color = ballColor,
                radius = radius,
                center = ballPosition
            )
        }
    }
}

private fun randomOffset(radius: Float, width: Int, height: Int): Offset {
    return Offset(
        x = Random.nextInt(radius.roundToInt(), width - radius.roundToInt()).toFloat(),
        y = Random.nextInt(radius.roundToInt(), height - radius.roundToInt()).toFloat()
    )
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