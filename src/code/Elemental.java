package code;

import javax.swing.*;

public class Elemental {
    public static JFrame frame;
    public static Scene scene;

    public static void main(String[] args) {
        frame = new JFrame("Elements");
        frame.setSize(1000, 700);

        scene = new Scene();
        frame.add(scene);
        frame.addMouseListener(scene);
        frame.addKeyListener(scene);

        frame.setVisible(true);

        new Thread(new Runnable() { @Override public void run() {
            while (true) {
                scene.update();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }}).start();
    }
}
