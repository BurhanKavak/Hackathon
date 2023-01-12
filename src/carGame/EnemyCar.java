package carGame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EnemyCar extends Sprite {
    private boolean isVisible=false;
    private Image enemyCarImg;
    private int x;
    private int y;
    private int dy;
    Random random=new Random();
    int rnd= random.nextInt(4)+1;

    public EnemyCar(int x, int y) {
        super(x, y);
        initCar();
    }
    private void initCar() {
        loadImage("image\\araba"+rnd+".png");
        getImageDimensions();
    }

    public void move() {
        y += dy;
    }
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(enemyCarImg, 0, 0, null);
    }

}
