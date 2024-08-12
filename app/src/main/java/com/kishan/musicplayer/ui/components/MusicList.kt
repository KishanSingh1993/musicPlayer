package com.kishan.musicplayer.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kishan.musicplayer.ui.MainViewModel
import java.io.File

@Composable
fun MusicList(viewModel: MainViewModel = viewModel()) {
    val musicFiles by viewModel.musicFiles.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        if (musicFiles.isEmpty()) {
            Text(
                text = "No music files found.",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            musicFiles.forEach { file ->
                Text(
                    text = file.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.playMusic(file) }
                        .padding(16.dp)
                )
            }
        }
    }
}

/**
 * @Composable
 * fun MusicList(
 *     viewModel: MainViewModel = viewModel(),
 *     onSongClick: (File) -> Unit
 * ) {
 *     val musicFiles by viewModel.musicFiles.collectAsState()
 *
 *     LazyColumn {
 *         items(musicFiles) { file ->
 *             Text(
 *                 text = file.name,
 *                 modifier = Modifier
 *                     .fillMaxWidth()
 *                     .padding(16.dp)
 *                     .clickable { onSongClick(file) }
 *             )
 *         }
 *     }
 * }
 * */