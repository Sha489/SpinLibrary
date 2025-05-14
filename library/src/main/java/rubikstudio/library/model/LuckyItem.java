package rubikstudio.library.model;

import android.graphics.Bitmap;

/**
 * Created by kiennguyen on 11/5/16.
 */

public class LuckyItem {
    public String topText;
    public String secondaryText;
    public String currency;
    public int secondaryTextOrientation;
    public Bitmap icon;
    public Bitmap arrowImage;
    public int color;
    public int textColor;
    public boolean topReward;

    public int gradientColor1;
    public int gradientColor2;
    public int gradientColor3;
    public int gradientColor4;

    public LuckyItem(String secondaryText, String currency, int gradientColor1, int gradientColor2, int gradientColor3, int gradientColor4, int textColor, boolean topReward, Bitmap icon, Bitmap arrowImage) {
        this.gradientColor1 = gradientColor1;
        this.gradientColor2 = gradientColor2;
        this.gradientColor3 = gradientColor3;
        this.gradientColor4 = gradientColor4;
        this.currency = currency;
        this.secondaryText = secondaryText;
        this.textColor = textColor;
        this.topReward = topReward;
        this.icon = icon;
        this.arrowImage = arrowImage;
    }

    public LuckyItem(String secondaryText, int textColor) {
        this.secondaryText = secondaryText;
        this.textColor = textColor;
    }
}
