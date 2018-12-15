package com.zhel.musicplayer.ui.activities;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import utils.Utils;

import static com.zhel.musicplayer.ui.activities.PlaylistActivity.PLAYLIST_KEY;
import static com.zhel.musicplayer.ui.activities.PlaylistActivity.SONG_POSITION_KEY;

public class PlayerActivity extends AppCompatActivity {

    Repository repository = new RepositoryImpl(this);

    private List<Song> songs = new ArrayList<>();

    private TextView durationFull;
    private TextView durationEdit;
    private TextView songName;
    private TextView songArtist;
    private TextView songAlbum;
    private ImageView songPicture;

    private SeekBar songSeekBar;

    private ImageView playPausePicture;
    private ImageView playForwardPicture;
    private ImageView playRewindPicture;
    private int position;
    private int duration;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
//        Repository repository = new RepositoryImpl(this);
        Intent intent = getIntent();

        durationFull = findViewById(R.id.song_duration_full);
        durationEdit = findViewById(R.id.song_duration_edit);
        songName = findViewById(R.id.song_name);
        songArtist = findViewById(R.id.song_artist);
        songAlbum = findViewById(R.id.song_album);
        songPicture = findViewById(R.id.song_picture);
        songSeekBar = findViewById(R.id.song_seek_bar_duration);

        playPausePicture = findViewById(R.id.song_play_pause);
        playForwardPicture = findViewById(R.id.song_forward);
        playRewindPicture = findViewById(R.id.song_rewind);


        Playlist playlist = (Playlist) intent.getSerializableExtra(PLAYLIST_KEY);
        position = (int) intent.getSerializableExtra(SONG_POSITION_KEY);


        for (String fileName : playlist.getSongs()) {
            songs.add(repository.getSongByFileName(fileName));
        }

        Song chooseSong = songs.get(position);

        setSong(chooseSong, playlist.getPicture());




//        duration = Integer.valueOf(chooseSong.getDuration());
//        songSeekBar.setMax(duration / 1000);



        Handler mHandler = new Handler();
//Make sure you update Seekbar on UI thread
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    songSeekBar.setProgress(mCurrentPosition);
                    durationEdit.setText(getDurationString(mediaPlayer.getCurrentPosition()));
//
//                    if (mCurrentPosition == duration) {
//                        playForward(position, playlist.getPicture());
//                    }
                }
                mHandler.postDelayed(this, 1000);
            }
        });


        findViewById(R.id.back).setOnClickListener(back -> onBackPressed());
//        if (playPausePicture.getDrawable().equals(getResources().getDrawable(R.drawable.ic_play_circle_outline_black_48dp)))

//        AssetFileDescriptor afd = null;
        playPrepare(chooseSong);

        playPausePicture.setOnClickListener(play -> {
            playCurrent();
        });

        playForwardPicture.setOnClickListener(play -> {

            position =  (position + 1) % songs.size();
            resetSong(songs.get(position));

            setSong(songs.get(position), playlist.getPicture());
//                position, playlist.getPicture()

        });

        playRewindPicture.setOnClickListener(play -> {
            position = position - 1;
            if (position < 0)
                position = songs.size() - 1;

            resetSong(songs.get(position));
            setSong(songs.get(position), playlist.getPicture());
//            playRewind(position, playlist.getPicture())
        });

    }

//    private void playForward(int position, String picture) {
//        position =  (position + 1) % songs.size();
//        resetSong(songs.get(position));
//
//        setSong(songs.get(position), picture);
//    }
//
//    private void playRewind(int position, String picture) {
//        position = position - 1;
//        if (position < 0)
//            position = songs.size() - 1;
//
//        resetSong(songs.get(position));
//        setSong(songs.get(position), picture);
//    }

    private void setSong(Song chooseSong, String picture) {
        int duration = Integer.valueOf(chooseSong.getDuration());
        durationFull.setText(getDurationString(duration));
        songName.setText(chooseSong.getName());
        songArtist.setText(chooseSong.getArtist());
        songAlbum.setText(chooseSong.getAlbum());
        songPicture.setImageDrawable(Utils.getDrawableFromAssets(this, picture));
        playPausePicture.setImageResource(R.drawable.ic_play_circle_outline_black_48dp);

        songSeekBar.setMax(duration / 1000);
    }

    private void resetSong(Song chooseSong) {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.reset();
        playPrepare(chooseSong);
    }

    private void playPrepare(Song chooseSong) {
        try {
            AssetFileDescriptor afd = getAssets().openFd(chooseSong.getFileName());

            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playCurrent() {


        if (playPausePicture.getDrawable().getConstantState() == Objects.requireNonNull(getDrawable(R.drawable.ic_play_circle_outline_black_48dp)).getConstantState()) {

            playPausePicture.setImageResource(R.drawable.ic_pause_circle_outline_black_48dp);
            mediaPlayer.start();

        } else if (playPausePicture.getDrawable().getConstantState() == Objects.requireNonNull(getDrawable(R.drawable.ic_pause_circle_outline_black_48dp)).getConstantState()) {

            if (mediaPlayer.isPlaying()) {
                playPausePicture.setImageResource(R.drawable.ic_play_circle_outline_black_48dp);
                mediaPlayer.pause();
            }
        }

    }


    public String getDurationString(int durationMlSec) {
        String min = Integer.toString(durationMlSec / 60000);
        String sec = String.format("%02d", (durationMlSec % 60000) / 1000);
        return String.format("%s:%s", min, sec);
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            if (isFinishing()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
