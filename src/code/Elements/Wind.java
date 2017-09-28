package code.Elements;

import code.Elemental;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Wind extends Element {
    public Wind() {
        this.color = Elemental.scene.getBackground().darker();
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

        try {
            for (Integer direction : directions) {
                Element side = this.getItem(direction);
                if (side == null) {
                    continue;
                }
                if ((int) (Math.random() * 3) == 1) {
                    side = side.getItem(direction);
                }
                if (side == null) {
                    continue;
                }
                direction = (int) (Math.random() * 4) + 1;
                if (side.gravity && side.getItem(direction).isAir()) {
                    side.move(direction);
                }
            }
            int direction = (int) (Math.random() * 4) + 1;
            if (this.getItem(direction).isAir()) {
                this.move(direction);
            }

            if ((int) (Math.random() * 100) == 1) { this.delete(); }
        } catch (Exception e) {
            this.delete();
        }
    }
}
