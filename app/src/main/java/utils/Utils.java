package utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import com.zhel.musicplayer.domain.models.Song;

import java.io.FileDescriptor;
import java.io.IOException;

import kotlin.Metadata;

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

        String artist = "Unknown Artist";
        String duration= "0:00";
        String album = "Unknown Album";
        String name ="Unknown Name";
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        try {
            mmr.setDataSource(context.getAssets().openFd(songFile).getFileDescriptor());

            artist = "" + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            duration = "" + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            album = "" + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            name = "" + mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

        } catch (IOException e) {
            e.printStackTrace();
        }
        mmr.release();

        return new Song(name, duration, album, artist);
    }
}
