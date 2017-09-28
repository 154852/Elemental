package code.Elements;

import code.Elemental;
import code.Scene;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public abstract class Element {
    public Color color = Color.white;
    public String name = "Element";
    boolean gravity = true;
    public char triggerChar = ' ';
    boolean liquid = false;
    int chance;
    boolean flammable = true;

    public void update(int updates) {}

    public void updateCore(int updates) {
        this.update(updates);
        if (this.liquid) { this.liquid(); }
        if (this.gravity) { this.gravity(); }
    }

    private void gravity() {
        Element element = this.getItem(1);
        if (element == null) { return; }

        if (element.isAir()) {
            this.move(1);
        }

    }

    private void liquid() {
        Element below = this.getItem(1);
        if (below == null || below.isAir()) {return;}

        Element e1 = this.getItem(2);
        Element e2 = this.getItem(4);
        if (e1 == null || e2 == null) {return;}
        if (e1.isAir() && e2.isAir()) {
            int rand = 1 + (int) (System.currentTimeMillis() % 2);
            this.move(rand * 2);
        } else if (e2.isAir()) {
            this.move(4);
        } else if (e1.isAir()) {
            this.move(2);
        }

    }

    public boolean isAir() {
        return this.getClass().equals(Air.class);
    }

    Point getPos() {
        for (int a = 0; a < Elemental.scene.items.size(); a++) {
            for (int b = 0; b < Elemental.scene.items.get(a).size(); b++) {
                if (Elemental.scene.items.get(a).get(b).equals(this)) {
                    return new Point(a, b);
                }
            }
        }
        return null;
    }

    void delete() {
        Point pos = this.getPos();
        Elemental.scene.items.get(pos.x).set(pos.y, new Air());
    }

    void move(int side) {
        Point pos = this.getPos();

        if (pos == null) { return; }

        Element newItem = new Air();
        try {
            newItem = this.getClass().newInstance();
        } catch (Exception e) { e.printStackTrace(); }


        switch (side) {
            case 1:
                Elemental.scene.items.get(pos.x).set(pos.y + 1, newItem);
                break;
            case 2:
                Elemental.scene.items.get(pos.x + 1).set(pos.y, newItem);
                break;
            case 3:
                Elemental.scene.items.get(pos.x).set(pos.y - 1, newItem);
                break;
            case 4:
                Elemental.scene.items.get(pos.x - 1).set(pos.y, newItem);
                break;
        }
        Elemental.scene.items.get(pos.x).set(pos.y, new Air());

    }

    Point find(Element e) {
        for (int a = 0; a < Elemental.scene.items.size(); a++) {
            for (int b = 0; b < Elemental.scene.items.get(a).size(); b++) {
                if (Elemental.scene.items.get(a).get(b).equals(e)) {
                    return new Point(a, b);
                }
            }
        }

        return null;
    }

    Element getItem(int side) {
        Point pos = this.getPos();
        if (pos == null) { return null; }
        if (pos.y > 68) {
            this.delete();
            return null;
        }
        if (pos.x > 98 || pos.x < 2) { return null; }

        switch (side) {
            case 1:
                return Elemental.scene.items.get(pos.x).get(pos.y + 1);
            case 2:
                return Elemental.scene.items.get(pos.x + 1).get(pos.y);
            case 3:
                return Elemental.scene.items.get(pos.x).get(pos.y - 1);
            case 4:
                return Elemental.scene.items.get(pos.x - 1).get(pos.y);
        }
        return null;
    }
}
