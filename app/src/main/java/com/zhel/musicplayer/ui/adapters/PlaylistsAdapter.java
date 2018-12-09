package com.zhel.musicplayer.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.domain.models.Playlist;
import com.zhel.musicplayer.ui.viewholders.PlaylistViewHolder;

import java.util.List;

public class PlaylistsAdapter extends RecyclerView.Adapter<PlaylistViewHolder> {
    private Context context;
    private List<Playlist> playlists;

    private OnPlaylistClickListener onPlaylistClickListener;

    public PlaylistsAdapter(List<Playlist> playlists, Context context, OnPlaylistClickListener onPlaylistClickListener) {

        this.onPlaylistClickListener = onPlaylistClickListener;
        this.playlists = playlists;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.viewholder_playlist, viewGroup, false);

        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = viewGroup.getMeasuredWidth() / 2;

        view.setLayoutParams(lp);

        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {

        Playlist playlist = playlists.get(position);

        holder.bind(playlist);
        holder.itemView.setOnClickListener(view -> onPlaylistClickListener.onPlaylistClick(playlist));
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public interface OnPlaylistClickListener {
        void onPlaylistClick(Playlist playlist);
    }
}
