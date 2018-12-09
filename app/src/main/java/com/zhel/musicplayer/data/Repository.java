package com.zhel.musicplayer.data;

import com.zhel.musicplayer.domain.models.Playlist;
import com.zhel.musicplayer.domain.models.Song;

import java.util.List;

public interface Repository {

    /**
     * Получает список сохранённых плейлистов
     *
     * @return список плейлистов
     */
    List<Playlist> getPlaylists();

    /**
     * Получает объект песни по имени файла
     * @param fileName имя файла
     * @return объект песни
     **/

    Song getSongByFileName(String fileName);
}
