package carGame;

import java.util.Random;

public class EnemyCar extends Sprite {
    private int dy=1;
    Random random=new Random();


    public EnemyCar(int x, int y) {
        super(x, y);
        initCar();
    }
    private void initCar() {
        loadImage("image/araba"+random.nextInt(4)+1+".png");
        getImageDimensions();
    }
    public void move() {
        y += dy;
    }

}
