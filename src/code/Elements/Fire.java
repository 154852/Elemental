package code.Elements;

import code.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Fire extends Element {
    private static ArrayList<Color> colors = new ArrayList<>(Arrays.asList(new Color(181, 78, 45),
            new Color(181, 125, 66), new Color(181, 60, 26), Color.red));

    public Fire() {
        this.name = "Fire";
        this.color = new Color(181, 60, 26);
        this.triggerChar = 'f';
        this.gravity = false;
    }

    @Override public void update(int updates) {
        java.util.List<Integer> choices = Arrays.asList(1, 2, 3, 4);

        for (Integer choice : choices) {
            Element item = this.getItem(choice);
            if (item == null) {continue;}
            if (item.getClass().equals(Water.class)) {
                this.delete();
                return;
            }
        }

        Collections.shuffle(choices);

        int item = choices.get(0);
        if ((int) (Math.random() * 5) == 0) {
            Element below = this.getItem(item);

            if (below == null) {
                return;
            }
            if (below.isAir() || below.getClass().equals(Rock.class) || !below.flammable) {
                return;
            }
            Point pos = this.find(below);
            if (pos == null) {
                return;
            }
            Element fire = new Fire();
            fire.color = colors.get((int) (Math.random() * 3));
            Scene.items.get(pos.x).set(pos.y, fire);
        }

        if ((int) (Math.random() * 20) == 0) {
            this.delete();
        }
    }
}
