package utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

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
