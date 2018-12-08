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
import com.zhel.musicplayer.ui.adapters.SongsAdapter;

public class PlaylistActivity extends AppCompatActivity {

    private TextView playlistName;
    private RecyclerView songsListView;
    private Repository repository = new RepositoryImpl(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        Intent intent = getIntent();

        playlistName = findViewById(R.id.playlist_name);
        songsListView = findViewById(R.id.songs_list);

        playlistName.setText(((Playlist)(intent.getSerializableExtra("key"))).getName());

        this.findViewById(R.id.back).setOnClickListener(back -> onBackPressed());

        SongsAdapter adapter = new SongsAdapter(((Playlist)(intent.getSerializableExtra("key"))).getSongs(), ((Playlist)(intent.getSerializableExtra("key"))).getPicture(), this);
        songsListView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
