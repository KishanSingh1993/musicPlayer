//package com.kishan.musicplayer.ui
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.kishan.musicplayer.ui.components.MusicListScreen
//
//
//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@Composable
//fun MusicPlayerNavHost(navController: NavHostController = rememberNavController()) {
//    NavHost(navController = navController, startDestination = "musicList") {
//        composable("musicList") { MusicListScreen(navController) }
//        composable("musicPlayer/{songPath}") { backStackEntry ->
//            MusicPlayerScreen(navController)
//        }
//    }
//}