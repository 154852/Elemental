package code.Elements;

import code.Elemental;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Wind extends Element {
    public Wind() {
        this.color = Color.black;
        this.gravity = false;
        this.triggerChar = 'x';
        this.flammable = false;
        this.liquid = false;
        this.name = "Wind";
    }

    @Override
    public void update(int updates) {
        List<Integer> directions = Arrays.asList(1, 2, 3, 4);
        Collections.shuffle(directions);

        for (Integer direction : directions) {
            Element side = this.getItem(direction);
            if (side == null) {continue;}
            if ((int)(Math.random() * 3) == 1) { side = side.getItem(direction); }
            if (side == null) {continue;}
            if (side.gravity && side.getItem(direction).isAir()) {
                side.move((int) (Math.random() * 4));
                this.move((int) (Math.random() * 4));
            }
        }

        if ((int) (Math.random() * 50) == 1) { this.delete(); }
    }
}
