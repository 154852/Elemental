package code.Elements;

import code.Elemental;

public class Wind extends Element {
    public Wind() {
        this.color = Elemental.scene.getBackground();
        this.gravity = false;
        this.triggerChar = 'x';
        this.flammable = false;
        this.liquid = false;
        this.name = "Wind";
    }
}
