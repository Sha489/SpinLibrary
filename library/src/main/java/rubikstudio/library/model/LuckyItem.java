package rubikstudio.library.model;

import android.graphics.Bitmap;

/**
 * Created by kiennguyen on 11/5/16.
 */

public class LuckyItem {
    public String topText;
    public String secondaryText;
    public int secondaryTextOrientation;
    public int icon;
    public int color;
    public Bitmap imageBitmap;
    public int firstGradient;
    public int secondGradient;

    public LuckyItem(String secondaryText, int color) {
        this.color = color;
        this.secondaryText = secondaryText;
    }

    public LuckyItem(int icon, int color){
        this.icon = icon;
        this.color = color;
    }

    public LuckyItem(Bitmap imageBitmap, int color){
        this.imageBitmap = imageBitmap;
        this.color = color;
    }

    public LuckyItem(String secondaryText, int firstGradient, int secondGradient) {
        this.secondaryText = secondaryText;
        this.firstGradient = firstGradient;
        this.secondGradient = secondGradient;
    }
}
