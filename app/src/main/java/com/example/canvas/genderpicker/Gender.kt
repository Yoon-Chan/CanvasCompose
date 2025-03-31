package com.example.canvas.genderpicker

sealed class Gender {
    data object Male: Gender()
    data object Female: Gender()
}