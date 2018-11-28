package com.zhel.musicplayer.data.impl;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zhel.musicplayer.data.Repository;
import com.zhel.musicplayer.domain.models.Playlist;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
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
