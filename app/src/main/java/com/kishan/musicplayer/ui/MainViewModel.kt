package com.kishan.musicplayer.ui

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kishan.musicplayer.data.MusicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import kotlin.concurrent.fixedRateTimer

class MainViewModel(private val repository: MusicRepository) : ViewModel() {

    private val _musicFiles = MutableStateFlow<List<File>>(emptyList())
    val musicFiles: StateFlow<List<File>> = _musicFiles

    private var mediaPlayer: MediaPlayer? = null
    private var progressTimer: java.util.Timer? = null

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private val _currentPosition = MutableStateFlow(0)
    val currentPosition: StateFlow<Int> = _currentPosition

    private val _currentTrackDuration = MutableStateFlow(0)
    val currentTrackDuration: StateFlow<Int> = _currentTrackDuration

    private val _selectedSong = MutableStateFlow<File?>(null)
    val selectedSong: StateFlow<File?> = _selectedSong

    fun loadMusicFiles() {
        viewModelScope.launch {
            val files = repository.getMusicFiles()
            _musicFiles.value = files
        }
    }

    fun selectSong(file: File) {
        _selectedSong.value = file
        playMusic(file)
    }

    fun playMusic(file: File) {
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer().apply {
            setDataSource(file.path)
            prepare()
            start()
            _currentTrackDuration.value = duration
        }

        startProgressTracking()
        _isPlaying.value = true
    }

    fun togglePlayPause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                stopProgressTracking()
                _isPlaying.value = false
            } else {
                it.start()
                startProgressTracking()
                _isPlaying.value = true
            }
        }
    }

    private fun startProgressTracking() {
        stopProgressTracking()
        progressTimer = fixedRateTimer("progressTimer", false, 0L, 1000L) {
            _currentPosition.value = mediaPlayer?.currentPosition ?: 0
        }
    }

    private fun stopProgressTracking() {
        progressTimer?.cancel()
        progressTimer = null
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
        stopProgressTracking()
    }
}


//class MainViewModel(private val repository: MusicRepository) : ViewModel() {
//
//    private val _musicFiles = MutableStateFlow<List<File>>(emptyList())
//    val musicFiles: StateFlow<List<File>> = _musicFiles
//
//    private val _currentTrack = MutableStateFlow<File?>(null)
//    val currentTrack: StateFlow<File?> = _currentTrack
//
//    private var mediaPlayer: MediaPlayer? = null
//    private var progressTimer: java.util.Timer? = null
//
//    private val _isPlaying = MutableStateFlow(false)
//    val isPlaying: StateFlow<Boolean> = _isPlaying
//
//    private val _currentPosition = MutableStateFlow(0)
//    val currentPosition: StateFlow<Int> = _currentPosition
//
//    private val _currentTrackDuration = MutableStateFlow(0)
//    val currentTrackDuration: StateFlow<Int> = _currentTrackDuration
//
//    fun loadMusicFiles() {
//        viewModelScope.launch {
//            _musicFiles.value = repository.getMusicFiles()
//        }
//    }
//
//    fun playMusic(file: File) {
//        _currentTrack.value = file
//        mediaPlayer?.release()
//        mediaPlayer = MediaPlayer().apply {
//            setDataSource(file.path)
//            prepare()
//            start()
//            _currentTrackDuration.value = duration
//        }
//        startProgressTracking()
//        _isPlaying.value = true
//    }
//
//    fun togglePlayPause() {
//        mediaPlayer?.let {
//            if (it.isPlaying) {
//                it.pause()
//                stopProgressTracking()
//                _isPlaying.value = false
//            } else {
//                it.start()
//                startProgressTracking()
//                _isPlaying.value = true
//            }
//        }
//    }
//
//    private fun startProgressTracking() {
//        stopProgressTracking()
//        progressTimer = fixedRateTimer("progressTimer", false, 0L, 1000L) {
//            _currentPosition.value = mediaPlayer?.currentPosition ?: 0
//        }
//    }
//
//    private fun stopProgressTracking() {
//        progressTimer?.cancel()
//        progressTimer = null
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        mediaPlayer?.release()
//        mediaPlayer = null
//        stopProgressTracking()
//    }
//
//    fun pauseMusic() {
//        mediaPlayer?.pause()
//    }
//
//    fun stopMusic() {
//        mediaPlayer?.stop()
//        _currentTrack.value = null
//    }
//
////    override fun onCleared() {
////        super.onCleared()
////        mediaPlayer?.release()
////    }
//}
