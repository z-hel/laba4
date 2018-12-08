package com.zhel.musicplayer.ui.viewholders;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.domain.models.Song;

import utils.Utils;

public class SongsListViewHolder extends RecyclerView.ViewHolder {

    private TextView songName;
    private TextView songArtist;
    private TextView songDuration;
    private ImageView songPicture;

    public SongsListViewHolder(@NonNull View itemView) {
        super(itemView);

        songName = itemView.findViewById(R.id.songs_list_song_name);
        songArtist = itemView.findViewById(R.id.songs_list_song_artist);
        songDuration = itemView.findViewById(R.id.songs_list_song_duration);
        songPicture = itemView.findViewById(R.id.songs_list_song_picture);
    }
    public void bind(Song song, String albumPicture) {

        int duration = Integer.valueOf(song.getDuration());

        String min =  Integer.toString(duration / 60000);
        String sec =  Integer.toString((duration % 60000) / 1000);
        if (sec.length() == 1) {
            sec = "0" + sec;
        }

        songPicture.setImageDrawable(Utils.getDrawableFromAssets(itemView.getContext(), albumPicture));
        songName.setText(song.getName());
        songArtist.setText(song.getArtist());
        songDuration.setText(String.format("%s:%s", min, sec));
    }
}
