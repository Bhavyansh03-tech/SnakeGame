package com.example.snakegame

import com.example.snakegame.model.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

class Game(private val scope: CoroutineScope) {

    // We have shared variable move that will be accessed by the main thread
    // and the coroutine thread.

    // So we should probably impl. some sync for some future problems.

    // It can be fixed by using Mutex that provide a mutual exclusion for coroutines using
    // vivlock method i execute the given action under this mutex lock :

    private val mutex = Mutex()

    private val mutableState : MutableStateFlow<State> =
        MutableStateFlow(
            State(
                food = Pair(5, 5),
                snake = listOf(Pair(7, 7))
            )
        )

    val state : Flow<State> = mutableState

    // Movement OF Snake :->
    var move = Pair(1,0)
        set(value) {
            scope.launch {
                mutex.withLock { field = value }
            }
        }

    // Create a function which is responsible for everything related to
    // moving the snake, checking collisions & updating the game state :->
    init {
        // We need a coroutine to run endless game loop as routine out of the main thread :
        scope.launch {
            var snakeLength = 4

            while (true) {
                delay(150)
                mutableState.update {
                    // Computing new position of the snake head :->
                    val newPosition = it.snake.first().let { poz->
                        mutex.withLock {
                            Pair(
                                (poz.first + move.first) % Sizes.BOARD_SIZE,
                                (poz.second + move.second) % Sizes.BOARD_SIZE
                            )
                        }
                    }

                    // Check that snake found the food or not :->
                    if (newPosition == it.food) {
                        snakeLength++
                    }

                    // Check collision :->
                    if (it.snake.contains(newPosition)) {
                        snakeLength = 4
                    }

                    it.copy(
                        // Recalculating new food position :->
                        food = if (newPosition == it.food) Pair(
                            Random.nextInt(Sizes.BOARD_SIZE),
                            Random.nextInt(Sizes.BOARD_SIZE)
                        ) else it.food,

                        // Set new head position to the game state :->
                        snake = listOf(newPosition) + it.snake.take(snakeLength - 1)
                    )
                }
            }
        }
    }
}