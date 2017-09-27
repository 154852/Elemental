package code.Elements;

import code.Scene;

import java.awt.*;
import java.util.*;

public class Acid extends Element {
    public Acid() {
        this.name = "Acid";
        this.color = Color.green;
        this.triggerChar = 'a';
        this.liquid = true;
        this.flammable = false;
    }

    @Override public void update(int updates) {
        java.util.List<Integer> choices = Arrays.asList(1, 2, 3, 4);
        Collections.shuffle(choices);

        for (Integer item : choices) {
            Element below = this.getItem(item);

            if (below == null) {return;}
            if (below.isAir() || below.getClass().equals(Rock.class) || below.getClass().equals(Acid.class)) {
                return;
            }
            Point pos = this.find(below);
            if (pos == null) {
                return;
            }
            Scene.items.get(pos.x).set(pos.y, new Air());
            this.delete();
        }

    }

}
