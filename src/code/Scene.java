package code;

import code.Elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Scene extends JPanel implements MouseListener, KeyListener {
    public ArrayList<ArrayList<Element>> items = new ArrayList<>();
    private static ArrayList<Class<? extends Element>> elements = new ArrayList<>(Arrays.asList
            (Powder.class, Acid.class, Rock.class, Water.class, Stone.class, Lava.class, Bomb.class, Tree.class,
            Fire.class, Oil.class, Wind.class));
    private boolean mouseDown = false;
    private int updates = 0;
    private boolean isRight = false;
    private Class<? extends Element> selected = Powder.class;
    private boolean reset = false;
    private int radius = 1;

    Scene() {
        this.setBackground(Color.darkGray);

        this.setList();
    }

    private void setList() {
        for (int i = 0; i < Elemental.frame.getSize().width; i += 10) {
            ArrayList<Element> list = new ArrayList<>();
            for (int a = 0; a < 70; a++) {
                list.add(new Air());
            }
            items.add(list);
        }

        this.border("x", 0);
        this.border("x", 67);
        this.border("y", 0);
        this.border("y", 99);
    }

    private void border(String dir, int place) {
        if (dir.equals("x")) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).set(place, new Rock());
            }
        } else {
            ArrayList<Element> list = items.get(place);
            for (int i = 0; i < list.size(); i++) {
                items.get(place).set(i, new Rock());
            }
        }
    }

    void update() {
        if (this.mouseDown) {
            if (!this.isRight) {
                this.addItem(this.getMousePosition());
            } else {
                this.removeItem(this.getMousePosition());
            }
        }
        this.repaint();
        if (this.reset) { return; }
        this.updates++;
        ArrayList<ArrayList<Element>> newList = new ArrayList<>();
        newList.addAll(items);
        Collections.shuffle(newList);
        for (ArrayList<Element> elements : newList) {
            for (Element element : elements) {
                if (element.updatAble) {
                    element.updateCore(this.updates);
                }
            }
        }
    }

    private void addItem(Point loc) {
        if (loc == null) { return; }
        int x = loc.x;
        int y = loc.y;

        int part1 = Math.round(x / 10);
        int part2 = Math.round(y / 10);

        try {
            if (items.get(part1).get(part2).isAir()) {
                int r = (int) (this.radius / 2);
                for (int i = -r; i < this.radius; i++) {
                    if (part1 + i < 1 || part1 + i > 99) {
                        continue;
                    }
                    if (items.get(part1 + i).get(part2).isAir()) {
                        items.get(part1 + i).set(part2, this.selected.newInstance());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeItem(Point loc) {
        if (loc == null) { return; }
        int x = loc.x;
        int y = loc.y;

        int part1 = Math.round(x / 10);
        int part2 = Math.round(y / 10);

        items.get(part1).set(part2, new Air());
    }

    @Override public void paint(Graphics g) {
        for (int a = 0; a < items.size(); a++) {
            for (int b = 0; b < items.get(a).size(); b++) {
                Element element = items.get(a).get(b);

                g.setColor(element.color);
                Graphics2D g2d = (Graphics2D) g;
                g2d.fillRect(a * 10, b * 10, 9, 9);
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) { }
    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'R') {
            this.reset = true;
            try { Thread.sleep(1000); } catch (Exception exc) { exc.printStackTrace(); }
            items.clear();
            this.setList();
            this.reset = false;
        } else if (e.getKeyChar() == '+') {
            this.radius += 1;
        } else if (e.getKeyChar() == '-') {
            this.radius -= 1;
        } else if (e.getKeyChar() == 'P') {
            this.reset = !this.reset;
        } else {
            for (Class<? extends Element> element : Scene.elements) {
                try {
                    if (element.newInstance().triggerChar == e.getKeyChar() &&
                            element.newInstance().triggerChar != ' ') {
                        this.selected = element;
                        return;
                    }
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        }

    }
    @Override public void keyReleased(KeyEvent e) { }

    @Override public void mouseClicked(MouseEvent e) { }
    @Override public void mousePressed(MouseEvent e) {
        if (e.isShiftDown()) {
            this.addItem(this.getMousePosition());
            return;
        }
        this.isRight = e.getButton() == 3;
        this.mouseDown = true;
    }

    @Override public void mouseReleased(MouseEvent e) {
        this.mouseDown = false;
        this.isRight = false;
    }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }

}
