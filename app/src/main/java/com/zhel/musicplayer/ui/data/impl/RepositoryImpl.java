package com.zhel.musicplayer.ui.data.impl;

import com.zhel.musicplayer.ui.data.Repository;
import com.zhel.musicplayer.ui.domain.models.Playlist;
import com.zhel.musicplayer.ui.domain.models.Song;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {

    @Override
    public List<Playlist> getPlaylists() {

        List<Playlist> r = new ArrayList<Playlist>() {{
            add(new Playlist("playlist1", "asdas", new ArrayList<Song>()));
            add(new Playlist("playlist2", "asdas", new ArrayList<Song>()));
            add(new Playlist("playlist3", "asdas", new ArrayList<Song>()));
            add(new Playlist("playlist4", "asdas", new ArrayList<Song>()));
            add(new Playlist("playlist5", "asdas", new ArrayList<Song>()));
        }};

        // todo implement

        return r;
    }
}
