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

}
