package com.kishan.musicplayer


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.kishan.musicplayer.data.MusicRepository
import com.kishan.musicplayer.ui.MainViewModel
import com.kishan.musicplayer.ui.MainViewModelFactory
import com.kishan.musicplayer.ui.MusicPlayerScreen
import com.kishan.musicplayer.ui.theme.MusicPlayerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = MusicRepository(applicationContext)
        val viewModel = ViewModelProvider(this, MainViewModelFactory(repository))
            .get(MainViewModel::class.java)

        setContent {
            MusicPlayerTheme {
                MusicPlayerScreen(viewModel = viewModel)
            }
        }
    }
}

/**
 * class MainActivity : ComponentActivity() {
 *     @RequiresApi(Build.VERSION_CODES.TIRAMISU)
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *
 *         val repository = MusicRepository(applicationContext)
 *         val viewModel = ViewModelProvider(this, MainViewModelFactory(repository))
 *             .get(MainViewModel::class.java)
 *
 *         setContent {
 *             MusicPlayerTheme {
 *                 // A surface container using the 'background' color from the theme
 *                 Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
 *                     MusicPlayerNavHost(viewModel = viewModel)
 *                 }
 *             }
 *         }
 *     }
 * }
 *
 *
 *
 * */