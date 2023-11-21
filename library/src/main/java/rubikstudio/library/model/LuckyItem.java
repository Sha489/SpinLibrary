package rubikstudio.library.model;

import android.graphics.drawable.Drawable;

/**
 * Created by kiennguyen on 11/5/16.
 */

public class LuckyItem {
    public String topText;
    public String secondaryText;
    public int secondaryTextOrientation;
    public int icon;
    public int color;
    public int firstGradient;
    public int secondGradient;
    public Drawable bitmapIcon;

    public LuckyItem(String secondaryText, int color) {
        this.color = color;
        this.secondaryText = secondaryText;
    }

    public LuckyItem(int icon, int firstGradient, int secondGradient) {
        this.icon = icon;
        this.firstGradient = firstGradient;
        this.secondGradient = secondGradient;
    }

    public LuckyItem(Drawable bitmapIcon, int firstGradient, int secondGradient) {
        this.bitmapIcon = bitmapIcon;
        this.firstGradient = firstGradient;
        this.secondGradient = secondGradient;
    }

}
