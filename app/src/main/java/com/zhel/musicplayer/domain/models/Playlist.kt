package com.zhel.musicplayer.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Playlist(
        @SerializedName("name")
    val name: String,
        @SerializedName("picture")
    val picture: String,
        @SerializedName("songs")
    val songs: List<String>
) : Serializable