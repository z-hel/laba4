package com.zhel.musicplayer.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.domain.models.Song;
import com.zhel.musicplayer.ui.activities.PlaylistActivity;
import com.zhel.musicplayer.ui.viewholders.SongsListViewHolder;

import java.util.List;
import java.util.zip.Inflater;

import utils.Utils;

public class SongsAdapter extends RecyclerView.Adapter<SongsListViewHolder> {
    private List<String> songsList;
    private Context context;

    public SongsAdapter(List<String> songsList, Context context) {
        this.songsList = songsList;
        this.context = context;
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
        String songString = songsList.get(position);

        viewHolder.bind(songString);
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }
}
