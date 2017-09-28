package code.Elements;

import code.Elemental;
import code.Scene;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class Tree extends Element {
    public Tree() {
        this.color = new Color(145, 109, 50);
        this.name = "Tree";
        this.triggerChar = 't';
        this.gravity = false;
    }

    @Override
    public void update(int updates) {
        java.util.List<Integer> choices = Arrays.asList(1, 2, 3, 4);

        for (Integer choice : choices) {
            Element next = this.getItem(choice);
            if (next == null) {continue;}
            if (next.getClass().equals(Water.class)) {
                if (this.chance >= 3) { this.chance -= 3; }
            }
        }

        Collections.shuffle(choices);
        Integer item = choices.get(0);

        if (!(((int) (Math.random() * this.chance) == 0))) {return;}

        Element next = this.getItem(item);

        if (next == null) {return;}
        if (!next.isAir()) { return; }

        Point pos = this.find(next);
        if (pos == null) {
            return;
        }
        try {
            int rand = (int) (Math.random() * 5);
            if (rand < 3) {
                Elemental.scene.items.get(pos.x).set(pos.y, this.getClass().newInstance());
            } else {
                Elemental.scene.items.get(pos.x).set(pos.y, new Leaf());
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
