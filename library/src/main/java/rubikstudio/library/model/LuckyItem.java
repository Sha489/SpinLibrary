package rubikstudio.library.model;

/**
 * Created by kiennguyen on 11/5/16.
 */

public class LuckyItem {
    public String topText;
    public String secondaryText;
    public String currency;
    public int secondaryTextOrientation;
    public int icon;
    public int color;
    public int textColor;
    public boolean topReward;

    public int gradientColor1;
    public int gradientColor2;

    public LuckyItem(String secondaryText, String currency, int gradientColor1, int gradientColor2, int textColor, boolean topReward, int icon) {
        this.gradientColor1 = gradientColor1;
        this.gradientColor2 = gradientColor2;
        this.currency = currency;
        this.secondaryText = secondaryText;
        this.textColor = textColor;
        this.topReward = topReward;
        this.icon = icon;
    }

    public LuckyItem(String secondaryText, int textColor) {
        this.secondaryText = secondaryText;
        this.textColor = textColor;
    }
}
