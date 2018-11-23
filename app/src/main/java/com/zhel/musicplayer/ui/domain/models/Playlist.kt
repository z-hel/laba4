package com.zhel.musicplayer.ui.domain.models


data class Playlist(
    val name: String,
    val picture: String,
    val songs: List<Song>
)