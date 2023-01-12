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

        if (x < 115) {
            x = 115;
        }
        if (y < 1) {
            y = 1;
        }
        if (x > 412) {
            x = 412;
        }
        if (y > 600) {
            y = 600;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = -3*Board.gameSpeed;

        if (key == KeyEvent.VK_RIGHT)
            dx = 3*Board.gameSpeed;

        if (key == KeyEvent.VK_UP)
            dy = -3*Board.gameSpeed;

        if (key == KeyEvent.VK_DOWN)
            dy = 3*Board.gameSpeed;
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = 0;

        if (key == KeyEvent.VK_RIGHT)
            dx = 0;

        if (key == KeyEvent.VK_UP)
            dy = Board.gameSpeed;

        if (key == KeyEvent.VK_DOWN)
            dy = Board.gameSpeed;
    }
}
