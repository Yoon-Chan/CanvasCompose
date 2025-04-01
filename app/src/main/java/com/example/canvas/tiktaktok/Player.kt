package com.example.canvas.tiktaktok

sealed class Player(val symbol: Char) {
    data object X : Player('X')
    data object O : Player('O')

    operator fun not(): Player {
        return if (this is X) O else X
    }
}

