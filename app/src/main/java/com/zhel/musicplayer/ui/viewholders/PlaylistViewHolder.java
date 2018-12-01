package com.zhel.musicplayer.ui.viewholders;

import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.domain.models.Playlist;

import utils.Utils;

public class PlaylistViewHolder extends RecyclerView.ViewHolder {
    private ImageView picture;
    private TextView name;
    private TextView count;


    public PlaylistViewHolder(@NonNull View itemView) {
        super(itemView);
        picture = itemView.findViewById(R.id.playlist_picture);
        name = itemView.findViewById(R.id.playlist_name);
        count = itemView.findViewById(R.id.playlist_song_count);
    }

    public void bind(Playlist playlist) {
        picture.setImageDrawable(Utils.getDrawableFromAssets(itemView.getContext(), playlist.getPicture()));
        picture.setMinimumHeight(picture.getWidth());
        name.setText(playlist.getName());
        count.setText(String.format("Песен: %s", Integer.toString(playlist.getSongs().size())));
    }
}
