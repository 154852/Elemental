package code.Elements;

import java.awt.*;

public class Rock extends Element {
    public Rock() {
        this.color = Color.lightGray;
        this.name = "Rock";
        this.gravity = false;
        this.triggerChar = 'r';
    }
}
