package rubikstudio.library.model;

/**
 * Created by kiennguyen on 11/5/16.
 */

public class LuckyItem {
    public String topText;
    public String secondaryText;
    public int secondaryTextOrientation;
    public int icon;
    public int color;
    public int textColor;

    public LuckyItem(String secondaryText, int color, int textColor) {
        this.color = color;
        this.secondaryText = secondaryText;
        this.textColor = textColor;
    }
}
