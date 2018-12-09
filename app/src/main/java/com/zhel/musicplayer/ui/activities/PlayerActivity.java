package com.zhel.musicplayer.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.data.Repository;
import com.zhel.musicplayer.data.impl.RepositoryImpl;
import com.zhel.musicplayer.domain.models.Playlist;
import com.zhel.musicplayer.domain.models.Song;

import java.util.ArrayList;
import java.util.List;

import utils.Utils;

import static com.zhel.musicplayer.ui.activities.PlaylistActivity.PLAYLIST_KEY;
import static com.zhel.musicplayer.ui.activities.PlaylistActivity.SONG_POSITION_KEY;

public class PlayerActivity extends AppCompatActivity{

    private TextView durationFull;
    private TextView durationEdit;
    private TextView songName;
    private TextView songArtist;
    private TextView songAlbum;
    private ImageView songPicture;
    private SeekBar songSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Repository repository = new RepositoryImpl(this);
        Intent intent = getIntent();

        durationFull = findViewById(R.id.song_duration_full);
        durationEdit = findViewById(R.id.song_duration_edit);
        songName = findViewById(R.id.song_name);
        songArtist = findViewById(R.id.song_artist);
        songAlbum = findViewById(R.id.song_album);
        songPicture = findViewById(R.id.song_picture);
        songSeekBar = findViewById(R.id.song_seek_bar_duration);

        Playlist playlist = (Playlist) intent.getSerializableExtra(PLAYLIST_KEY);
        int position = (int) intent.getSerializableExtra(SONG_POSITION_KEY);

        List<Song> songs = new ArrayList<>();
        for (String fileName: playlist.getSongs()) {
            songs.add(repository.getSongByFileName(fileName));
        }

        Song chooseSong = songs.get(position);

        int duration = Integer.valueOf(chooseSong.getDuration());

        String min =  Integer.toString(duration / 60000);
        String sec =  Integer.toString((duration % 60000) / 1000);
        sec = sec.length() == 1 ? "0" + sec : sec;
//        if (sec.length() == 1) {
//            sec = "0" + sec;
//        }

        durationFull.setText(String.format("%s:%s", min, sec));
        durationEdit.setText("0:00");
        songName.setText(chooseSong.getName());
        songArtist.setText(chooseSong.getArtist());
        songAlbum.setText(chooseSong.getAlbum());
        songPicture.setImageDrawable(Utils.getDrawableFromAssets(this, playlist.getPicture()));

    }
}
