package com.zhel.musicplayer.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.ui.domain.models.Playlist;
import com.zhel.musicplayer.ui.viewholders.PlaylistViewHolder;

import java.util.List;

public class PlaylistsAdapter extends RecyclerView.Adapter<PlaylistViewHolder>{
    private final Context context;
    private List<Playlist> playlists;

    public PlaylistsAdapter(List<Playlist> playlists, Context context) {
        this.playlists = playlists;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.viewholder_playlist, viewGroup,false);

        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {

        holder.bind(playlists.get(position));
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }
}
