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
        loadImage("image\\oyuncuarabası.png");
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
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
            dy--;

        if (key == KeyEvent.VK_DOWN)
            dy--;
    }
}
