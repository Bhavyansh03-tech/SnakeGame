package com.example.snakegame.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.snakegame.Sizes
import com.example.snakegame.model.State

@Composable
fun Board(state: State) {

    BoxWithConstraints(
        modifier = Modifier.padding(20.dp)
    ) {
        val tileSize = maxWidth / Sizes.BOARD_SIZE

        // Board Background :->
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .size(maxWidth)
                .border(2.dp, MaterialTheme.colorScheme.primary)
        )

        // Food :->
        Box(
            modifier = Modifier
                .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                .size(tileSize)
                .background(MaterialTheme.colorScheme.primary, CircleShape)
        )

        // Snake Body :->
        state.snake.forEach {
            Box(
                modifier = Modifier
                    .offset(x = tileSize * it.first, y = tileSize * it.second)
                    .size(tileSize)
                    .background(MaterialTheme.colorScheme.primary, Shapes().small)
            )
        }
    }

}