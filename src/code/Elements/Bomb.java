package code.Elements;

import code.Elemental;
import code.Scene;

import java.awt.*;

public class Bomb extends Element {
    public Bomb() {
        this.color = new Color(25, 186, 22);
        this.name = "Bomb";
        this.triggerChar = 'b';
        this.flammable = false;
    }

    @Override
    public void update(int updates) {
        Element bottom = this.getItem(1);
        if (bottom == null || bottom.isAir() ||
                bottom.getClass().equals(Bomb.class) ||
                bottom.getClass().equals(Rock.class)) {return;}
        Point pos = this.getPos();

        int r = 2;
        for (int a = -r; a <= r; a++) {
            for (int b = -r; b <= r; b++) {
                if (pos.x + a >= 100 || pos.x + a <= 0) {continue;}
                if (Elemental.scene.items.get(pos.x + a).get(pos.y + b).getClass().equals(Rock.class) ||
                        Elemental.scene.items.get(pos.x + a).get(pos.y + b).liquid) { continue; }
                Elemental.scene.items.get(pos.x + a).set(pos.y + b, new Air());
            }
        }
    }
}
