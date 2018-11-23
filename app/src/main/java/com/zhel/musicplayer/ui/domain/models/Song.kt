package com.zhel.musicplayer.ui.domain.models

/**
 * Класс описания объекта
 *
 * @param name название песни
 * @param duration длительность песни в секундах
 * @param album название альбома
 * @param artist исполнитель
 * */
data class Song(
        val name: String,
        val duration: Int,
        val album: String,
        val artist: String
)