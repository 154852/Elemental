package code.Elements;

import java.awt.*;

public class Stone extends Element {
    public Stone() {
        this.name = "Stone";
        this.triggerChar = 's';
        this.color = Color.gray;
        this.flamable = false;
    }
}
