package com.zhel.musicplayer.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.zhel.musicplayer.R
import com.zhel.musicplayer.domain.models.Playlist

class PlaylistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        findViewById<TextView>(R.id.text).text = (intent.getSerializableExtra("key") as Playlist).name
    }
}
