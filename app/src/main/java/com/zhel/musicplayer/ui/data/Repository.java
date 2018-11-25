package com.zhel.musicplayer.ui.data;

import android.provider.MediaStore;

import com.zhel.musicplayer.ui.domain.models.Playlist;

import java.util.List;

public interface Repository {

    /**
     * Получает список сохранённых плейлистов
     *
     * @return список плейлистов
     */
    List<Playlist> getPlaylists();
}
