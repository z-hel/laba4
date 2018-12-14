package com.zhel.musicplayer.ui.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.data.Repository;
import com.zhel.musicplayer.data.impl.RepositoryImpl;

import utils.Utils;

public class PlayerActivity extends AppCompatActivity{

    Repository repository = new RepositoryImpl(this);

    private TextView durationFull;
    private TextView durationEdit;
    private TextView songName;
    private TextView songArtist;
    private TextView songAlbum;
    private ImageView songPicture;
    private SeekBar songSeekBar;

    private int flagPlay = 0;
    private int flagPause = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Intent intent = getIntent();

        durationFull = findViewById(R.id.song_duration_full);
        durationEdit = findViewById(R.id.song_duration_edit);
        songName = findViewById(R.id.song_name);
        songArtist = findViewById(R.id.song_artist);
        songAlbum = findViewById(R.id.song_album);
        songPicture = findViewById(R.id.song_picture);
        songSeekBar = findViewById(R.id.song_seek_bar_duration);


        int duration = Integer.valueOf(intent.getSerializableExtra("duration").toString());

        String min =  Integer.toString(duration / 60000);
        String sec =  Integer.toString((duration % 60000) / 1000);
        sec = sec.length() == 1 ? "0" + sec : sec;
//        if (sec.length() == 1) {
//            sec = "0" + sec;
//        }

        durationFull.setText(String.format("%s:%s", min, sec));
        durationEdit.setText("0:00");
        songName.setText(intent.getSerializableExtra("name").toString());
        songArtist.setText(intent.getSerializableExtra("artist").toString());
        songAlbum.setText(intent.getSerializableExtra("album").toString());
        songPicture.setImageDrawable(Utils.getDrawableFromAssets(this, intent.getSerializableExtra("picture").toString()));

        findViewById(R.id.back).setOnClickListener(back -> onBackPressed());
        findViewById(R.id.song_play_pause).setOnClickListener(play -> play(flagPlay));
    }

    public void play(int flagPlayOrPause) {
        if (flagPlayOrPause == flagPlay) {
            findViewById(R.id.song_play_pause).setBackgroundResource(R.drawable.ic_pause_circle_outline_black_48dp);
            MediaPlayer mediaPlayer = new MediaPlayer();
//            mediaPlayer.setDataSource(repository);
            mediaPlayer.start();
        }

//        int requestResult = requestAudioFocus();
//        if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//            internalPlay();
//        }
//        else if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_DELAYED) {
//            playOnFocusGain = true;
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
