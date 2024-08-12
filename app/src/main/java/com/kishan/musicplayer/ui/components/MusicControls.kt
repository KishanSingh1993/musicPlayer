package com.kishan.musicplayer.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kishan.musicplayer.ui.MainViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@SuppressLint("StateFlowValueCalledInComposition")

@Composable
fun MusicControls(viewModel: MainViewModel) {
    val isPlaying by viewModel.isPlaying.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()
    val currentTrackDuration by viewModel.currentTrackDuration.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        // Play/Pause Button
        IconButton(onClick = { viewModel.togglePlayPause() }) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play"
            )
        }

        // Song Progress
        Slider(
            value = currentPosition.toFloat(),
            valueRange = 0f..currentTrackDuration.toFloat(),
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        // Display current time and duration
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(formatDuration(currentPosition))
            Text(formatDuration(currentTrackDuration))
        }
    }
}

@SuppressLint("DefaultLocale")
fun formatDuration(duration: Int): String {
    val minutes = duration / 1000 / 60
    val seconds = duration / 1000 % 60
    return String.format("%02d:%02d", minutes, seconds)
}

//@Composable
//fun MusicControls(viewModel: MainViewModel = viewModel()) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        val currentTrack = viewModel.currentTrack.value
//        if (currentTrack != null) {
//            Button(onClick = { viewModel.pauseMusic() }) {
//                Text("Pause")
//            }
//            Button(onClick = { viewModel.stopMusic() }) {
//                Text("Stop")
//            }
//        }
//    }
//}