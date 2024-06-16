package com.example.snakegame.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.snakegame.Sizes.BUTTON_SIZE_COLOR

@Composable
fun Buttons(onDirectionChange: (Pair<Int, Int>) -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {

        Button(onClick = { onDirectionChange(Pair(0,-1)) }, modifier = BUTTON_SIZE_COLOR) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
        }

        Row {
            Button(onClick = { onDirectionChange(Pair(-1,0)) }, modifier = BUTTON_SIZE_COLOR) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }

            Spacer(modifier = Modifier.size(64.dp))

            Button(onClick = { onDirectionChange(Pair(1,0)) }, modifier = BUTTON_SIZE_COLOR) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
            }
        }

        Button(onClick = { onDirectionChange(Pair(0,1)) }, modifier = BUTTON_SIZE_COLOR) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }

    }

}