package com.kishan.musicplayer.ui

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.kishan.musicplayer.ui.components.MusicControls
import com.kishan.musicplayer.ui.components.MusicList

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MusicPlayerScreen(
    viewModel: MainViewModel = viewModel()) {
    val storagePermissionState = rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)


    val permissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(Manifest.permission.READ_MEDIA_AUDIO)
    } else {
        rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    LaunchedEffect(permissionState.status) {
        when (permissionState.status) {
            is PermissionStatus.Granted -> {
                viewModel.loadMusicFiles()
            }
            is PermissionStatus.Denied -> {
                permissionState.launchPermissionRequest()
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            MusicList(viewModel)
            MusicControls(viewModel)
        }
    }
}

//    LaunchedEffect(storagePermissionState.status) {
//        if (storagePermissionState.status.isGranted) {
//            viewModel.loadMusicFiles()
//        } else {
//            storagePermissionState.launchPermissionRequest()
//        }
//    }

//OR
//    LaunchedEffect(storagePermissionState.status) {
//        when (storagePermissionState.status) {
//            is PermissionStatus.Granted -> {
//                viewModel.loadMusicFiles()
//            }
//            is PermissionStatus.Denied -> {
//                storagePermissionState.launchPermissionRequest()
//            }
//        }
//    }