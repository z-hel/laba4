package com.zhel.musicplayer.data;

import com.zhel.musicplayer.domain.models.Playlist;

import java.util.List;

public interface Repository {

    /**
     * Получает список сохранённых плейлистов
     *
     * @return список плейлистов
     */
    List<Playlist> getPlaylists();
}
