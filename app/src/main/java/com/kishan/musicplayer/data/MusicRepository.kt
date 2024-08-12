package com.kishan.musicplayer.data

import android.content.Context
import android.os.Environment
import java.io.File

class MusicRepository(private val context: Context) {

//    fun getMusicFiles(): List<File> {
//        val musicDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
//        return musicDir?.listFiles()?.toList() ?: emptyList()
//    }

    fun getMusicFiles(): List<File> {
        val musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        return musicDir?.listFiles { file -> file.extension in listOf("mp3", "wav", "m4a") }?.toList() ?: emptyList()
    }
}