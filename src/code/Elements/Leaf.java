package code.Elements;

import java.awt.*;
import java.util.Arrays;

class Leaf extends Element {
    Leaf() {
        this.name = "Leaf";
        this.color = new Color(25, 186, 22);
        this.gravity = false;
    }

    @Override
    public void update(int updates) {
        java.util.List<Integer> choices = Arrays.asList(1, 2, 3, 4);

        int chc = 7;
        Element chosen = null;

        for (Integer choice : choices) {
            Element next = this.getItem(choice);
            if (next == null) {continue;}
            if (next.getClass().equals(Water.class)) { if (chc >= 3) { chc -= 3; } }
            else if (next.getClass().equals(Tree.class)) {chosen = next;}
        }
        if (chosen != null) {
            chosen.chance = chc;
        }
    }
}
