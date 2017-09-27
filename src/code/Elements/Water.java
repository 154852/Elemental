package code.Elements;

import java.awt.*;

public class Water extends Element {
    public Water() {
        this.name = "Water";
        this.triggerChar = 'w';
        this.liquid = true;
        this.color = Color.blue;
        this.flammable = false;
    }
}
