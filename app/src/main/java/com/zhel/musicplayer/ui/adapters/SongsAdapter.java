package com.zhel.musicplayer.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.domain.models.Song;
import com.zhel.musicplayer.ui.viewholders.SongsListViewHolder;

import java.util.List;

import utils.Utils;

public class SongsAdapter extends RecyclerView.Adapter<SongsListViewHolder> {

    private List<Song> songsList;
    private Context context;
    private String albumPicture;
    private OnSongClickListener onSongClickListener;

    public SongsAdapter(List<Song> songsList, String albumPicture, Context context, OnSongClickListener onSongClickListener) {
        this.songsList = songsList;
        this.albumPicture = albumPicture;
        this.context = context;
        this.onSongClickListener = onSongClickListener;
    }

    @NonNull
    @Override
    public SongsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.viewholder_songs_list, viewGroup, false);

        return new SongsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsListViewHolder viewHolder, int position) {

        Song song = songsList.get(position);

        viewHolder.bind(song, albumPicture);
        viewHolder.itemView.setOnClickListener(s -> onSongClickListener.onSongClick(position));
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public interface OnSongClickListener {
        void onSongClick(int songPosition);
    }
}
