package com.example.stopwatch


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StopwatchApp()
        }
    }
}

@Composable
fun StopwatchApp() {
    var seconds by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000)
            seconds++
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = String.format("%02d:%02d", seconds / 60, seconds % 60),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Button(onClick = { isRunning = true }) {
                Text("Start")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = { isRunning = false }) {
                Text("Stop")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = {
                isRunning = false
                seconds = 0
            }) {
                Text("Reset")
            }
        }
    }
}