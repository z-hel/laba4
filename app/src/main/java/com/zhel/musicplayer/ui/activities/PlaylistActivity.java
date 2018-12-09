package com.zhel.musicplayer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.data.Repository;
import com.zhel.musicplayer.data.impl.RepositoryImpl;
import com.zhel.musicplayer.domain.models.Playlist;
import com.zhel.musicplayer.domain.models.Song;
import com.zhel.musicplayer.ui.adapters.SongsAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    private TextView playlistName;
    private RecyclerView songsListView;
    private Repository repository = new RepositoryImpl(this);
    public static final String PLAYLIST_KEY = "PLAYLIST_KEY";
    public static final String SONG_POSITION_KEY = "SONG_POSITION_KEY";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent intent = getIntent();

        playlistName = findViewById(R.id.playlist_name);
        songsListView = findViewById(R.id.songs_list);

        Playlist playlist = (Playlist) (intent.getSerializableExtra(PLAYLIST_KEY));

        playlistName.setText(playlist.getName());

        this.findViewById(R.id.back).setOnClickListener(back -> onBackPressed());

        List<Song> songs = new ArrayList<>();

        for (String filename : playlist.getSongs()) {
            songs.add(repository.getSongByFileName(filename));
        }

        SongsAdapter adapter = new SongsAdapter(
                songs,
                playlist.getPicture(),
                this,
                songPosition -> {

                    Intent intentPlayer = new Intent(PlaylistActivity.this, PlayerActivity.class);

                    intentPlayer.putExtra(PLAYLIST_KEY, playlist);
                    intentPlayer.putExtra(SONG_POSITION_KEY, songPosition);

                    startActivity(intentPlayer);
                });
        songsListView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
