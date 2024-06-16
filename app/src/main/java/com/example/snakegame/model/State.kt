package com.example.snakegame.model

data class State(
    val food: Pair<Int, Int>,
    val snake: List<Pair<Int, Int>>
)
