package utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;

import com.zhel.musicplayer.domain.models.Song;

import java.io.IOException;

public class Utils {

    public static Drawable getDrawableFromAssets(Context context, String filename) {
        try {
            return Drawable.createFromStream(context.getAssets().open(filename), null);
        } catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }

    public static Song getSongFromAssets(Context context, String songFile) {

        String artist;
        String duration;
        String album;
        String name;
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            AssetFileDescriptor afd = context.getAssets().openFd(songFile);
            mmr.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

            artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            name = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

        } catch (IOException e) {
            artist = "Unknown Artist";
            duration = "0:00";
            album = "Unknown Album";
            name = "Unknown Name";
        }
        mmr.release();

        return new Song(name, duration, album, artist);
    }
}
