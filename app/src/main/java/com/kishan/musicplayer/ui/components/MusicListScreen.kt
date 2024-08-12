//package com.kishan.musicplayer.ui.components
//
//import android.Manifest
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.PlayArrow
//import androidx.compose.material.icons.filled.Pause
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.PermissionStatus
//import com.google.accompanist.permissions.rememberPermissionState
//import com.kishan.musicplayer.ui.MainViewModel
//
//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun MusicListScreen(
//    navController: NavController,
//    viewModel: MainViewModel = viewModel()
//) {
//    val storagePermissionState = rememberPermissionState(Manifest.permission.READ_MEDIA_AUDIO)
//
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
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Column {
//            MusicList(viewModel) { file ->
//                navController.navigate("musicPlayer/${file.path}")
//            }
//        }
//    }
//}
