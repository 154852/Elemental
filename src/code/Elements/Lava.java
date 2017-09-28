package code.Elements;

import code.Elemental;
import code.Scene;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class Lava extends Element {
    public Lava() {
        this.name = "Lava";
        this.triggerChar = 'l';
        this.color = new Color(240, 94, 31);
        this.liquid = true;
        this.flammable = false;
    }

    @Override
    public void update(int updates) {
        java.util.List<Integer> choices = Arrays.asList(1, 2, 3, 4);
        Collections.shuffle(choices);

        for (Integer item : choices) {
            Element below = this.getItem(item);

            if (below == null) {return;}
            if (below.isAir() || below.getClass().equals(Rock.class) || below.getClass().equals(this.getClass())) {
                return;
            }

            Point pos = this.find(below);
            if (pos == null) { return; }
            if (below.getClass().equals(Water.class)) {
                Elemental.scene.items.get(pos.x).set(pos.y, new Stone());
                this.delete();
                return;
            }
            try {
                Elemental.scene.items.get(pos.x).set(pos.y, this.getClass().newInstance());
            } catch (Exception e) { e.printStackTrace(); }
            if (!below.getClass().equals(Stone.class)) {
                this.delete();
            }
        }
    }
}
