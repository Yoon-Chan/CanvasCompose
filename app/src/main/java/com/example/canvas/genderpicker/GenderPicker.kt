package com.example.canvas.genderpicker

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.canvas.R

@Composable
fun GenderPicker(
    modifier: Modifier = Modifier,
    maleGradient: List<Color> = listOf(Color(0xFF6D6DFF), Color.Blue),
    femaleGradient: List<Color> = listOf(Color(0xFFEA76FF), Color.Magenta),
    distanceBetweenGender: Dp = 50.dp,
    pathScaleFactor: Float = 7f,
    onGenderSelected: (Gender) -> Unit
) {
    var selectedGender by remember {
        mutableStateOf<Gender>(Gender.Female)
    }

    var center by remember {
        mutableStateOf(Offset.Unspecified)
    }

    val malePathString = stringResource(id = R.string.mail_path)
    val femalePathString = stringResource(id = R.string.female_path)

    //svg pathData를 Path객체로 반환하기
    val malePath = remember {
        PathParser().parsePathString(malePathString).toPath()
    }

    val femalePath = remember {
        PathParser().parsePathString(femalePathString).toPath()
    }

    //두 그림의 영역을 가져오는 방법
    val malePathBound = remember {
        malePath.getBounds()
    }

    val femalePathBound = remember {
        femalePath.getBounds()
    }

    var maleTranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var femaleTranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var currentClickOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    val maleSelectionRadius = animateFloatAsState(
        targetValue = if(selectedGender is Gender.Male) 80f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    val femaleSelectionRadius = animateFloatAsState(
        targetValue = if(selectedGender is Gender.Female) 80f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectTapGestures {
                    val transformedMaleRect = Rect(
                        offset = maleTranslationOffset,
                        size = malePathBound.size * pathScaleFactor
                    )

                    val transformedFemaleRect = Rect(
                        offset = femaleTranslationOffset,
                        size = femalePathBound.size * pathScaleFactor
                    )

                    if(selectedGender !is Gender.Male && transformedMaleRect.contains(it)) {
                        currentClickOffset = it
                        selectedGender = Gender.Male
                        onGenderSelected(Gender.Male)
                    } else if (selectedGender !is Gender.Female && transformedFemaleRect.contains(it)) {
                        currentClickOffset = it
                        selectedGender = Gender.Female
                        onGenderSelected(Gender.Female)
                    }
                }
            }
    ) {
        center = this.center
        maleTranslationOffset = Offset(
            x = center.x - malePathBound.width * pathScaleFactor - distanceBetweenGender.toPx() / 2 ,
            y = center.y - malePathBound.height * pathScaleFactor / 2f
        )

        femaleTranslationOffset = Offset(
            x = center.x + distanceBetweenGender.toPx() / 2f,
            y = center.y -  pathScaleFactor * femalePathBound.height / 2f
        )

        val untransformedMaleClickOffset = if(currentClickOffset == Offset.Zero) {
            malePathBound.center
        } else {
            (currentClickOffset - maleTranslationOffset) / pathScaleFactor
        }

        val untransformedFemaleClickOffset = if(currentClickOffset == Offset.Zero) {
            femalePathBound.center
        } else {
            (currentClickOffset - femaleTranslationOffset) / pathScaleFactor
        }

        translate(
            left = maleTranslationOffset.x,
            top = maleTranslationOffset.y
        ) {
            scale(
                scale = pathScaleFactor,
                pivot = malePathBound.topLeft
            ) {
                drawPath(
                    path = malePath,
                    color = Color.LightGray
                )
                clipPath(
                    path = malePath
                ) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = maleGradient,
                            center = untransformedMaleClickOffset,
                            radius = maleSelectionRadius.value + 1f
                        ),
                        center = untransformedMaleClickOffset,
                        radius = maleSelectionRadius.value
                    )
                }
            }
        }

        translate(
            left = femaleTranslationOffset.x,
            top = femaleTranslationOffset.y
        ) {
            scale(
                scale = pathScaleFactor,
                pivot = femalePathBound.topLeft
            ) {
                drawPath(
                    path = femalePath,
                    color = Color.LightGray
                )

                clipPath(
                    path = femalePath
                ) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = femaleGradient,
                            center = untransformedFemaleClickOffset,
                            radius = femaleSelectionRadius.value + 1f
                        ),
                        radius = femaleSelectionRadius.value,
                        center = untransformedFemaleClickOffset
                    )
                }
            }
        }
    }
}