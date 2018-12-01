package com.zhel.musicplayer.ui.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.domain.models.Song;

import utils.Utils;

public class SongsListViewHolder extends RecyclerView.ViewHolder {

    private TextView songName;
    private TextView songArtist;
    private TextView songDuration;

    public SongsListViewHolder(@NonNull View itemView) {
        super(itemView);

        songName = itemView.findViewById(R.id.songs_list_song_name);
        songArtist = itemView.findViewById(R.id.songs_list_song_artist);
        songDuration = itemView.findViewById(R.id.songs_list_song_duration);
    }
    public void bind(String songString) {
        Song song = Utils.getSongFromAssets(itemView.getContext(), songString);
        songName.setText(song.getName());
        songArtist.setText(song.getArtist());
        songDuration.setText(song.getDuration());
    }
}
