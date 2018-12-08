package com.zhel.musicplayer.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhel.musicplayer.R;
import com.zhel.musicplayer.domain.models.Playlist;
import com.zhel.musicplayer.ui.adapters.PlaylistsAdapter;
import com.zhel.musicplayer.data.Repository;
import com.zhel.musicplayer.data.impl.RepositoryImpl;

public class PlaylistsActivity extends AppCompatActivity {

    private static final int COLUMN_COUNT = 2;
    private Repository repository = new RepositoryImpl(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);

        RecyclerView playlistsView = findViewById(R.id.playlists);
        GridLayoutManager grid = new GridLayoutManager(this, COLUMN_COUNT);

        playlistsView.setLayoutManager(grid);
        playlistsView.addItemDecoration(new ItemDecoration(this));
        playlistsView.setAdapter(new PlaylistsAdapter(repository.getPlaylists(), this, playlist -> {

            Intent intent = new Intent(PlaylistsActivity.this, PlaylistActivity.class);

            intent.putExtra("key", playlist);

            startActivity(intent);
        }));
    }

    public static class ItemDecoration extends RecyclerView.ItemDecoration {

        private final int decorationMargin;

        public ItemDecoration(Context context) {
            decorationMargin = context.getResources().getDimensionPixelSize(R.dimen.playlists_viewholder_margin);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int itemPosition = parent.getChildAdapterPosition(view);
            int totalCount = parent.getAdapter().getItemCount();

            if (itemPosition >= 0 && itemPosition < totalCount - 1) {
                outRect.bottom = decorationMargin;
            }
            if (itemPosition % 2 == 0)
                outRect.right = decorationMargin / 2;
            else
                outRect.left = decorationMargin / 2;

        }
    }
}
