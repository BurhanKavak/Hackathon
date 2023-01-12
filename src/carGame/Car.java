package carGame;

import java.awt.event.KeyEvent;

public class Car extends Sprite {

    private int dx;
    private int dy;

    public Car(int x, int y) {
        super(x, y);

        initCar();
    }

    private void initCar() {
        loadImage("image/oyuncuarabasııı.png");
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }
        if (y < 1) {
            y = 1;
        }
        if (x > 500) {
            x = 500;
        }
        if (y > 700) {
            y = 700;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = -3;

        if (key == KeyEvent.VK_RIGHT)
            dx = 3;

        if (key == KeyEvent.VK_UP)
            dy = -3;

        if (key == KeyEvent.VK_DOWN)
            dy = 3;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = 0;

        if (key == KeyEvent.VK_RIGHT)
            dx = 0;

        if (key == KeyEvent.VK_UP)
            dy = +1;

        if (key == KeyEvent.VK_DOWN)
            dy = +1;
    }
}
