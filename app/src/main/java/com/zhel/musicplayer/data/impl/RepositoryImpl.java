package com.zhel.musicplayer.data.impl;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zhel.musicplayer.data.Repository;
import com.zhel.musicplayer.domain.models.Playlist;
import com.zhel.musicplayer.domain.models.Song;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private static final String DATA_JSON_FILENAME = "data.json";
    private final Context context;

    public RepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<Playlist> getPlaylists() {

        String json = readPlaylistsJson();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<ArrayList<Playlist>>(){}.getType();
        return gson.fromJson(json, listType);
    }

    @Override
    public Song getSongByFileName(String fileName) {
        String artist;
        String duration;
        String album;
        String name;

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();

        try {
            AssetFileDescriptor afd = context.getAssets().openFd(fileName);
            mmr.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

            artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            name = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

        } catch (IOException e) {
            artist = "Unknown Artist";
            duration = "0";
            album = "Unknown Album";
            name = "Unknown Name";
        }
        mmr.release();

        return new Song(name, duration, album, artist, fileName);
    }

    private String readPlaylistsJson() {
        String json;
        try {
            InputStream is = context.getAssets().open(DATA_JSON_FILENAME);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
