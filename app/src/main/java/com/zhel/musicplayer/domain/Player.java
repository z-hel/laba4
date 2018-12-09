package com.zhel.musicplayer.domain;

import com.zhel.musicplayer.domain.models.Song;

public interface Player {

    void play(Song song);

    void stop();
}
