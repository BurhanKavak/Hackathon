package carGame;

import java.util.Random;

public class EnemyCar extends Sprite {

    private int dy=2*Board.gameSpeed;
    Random random=new Random();
    int rnd= random.nextInt(5)+1;

    public EnemyCar(int x, int y) {
        super(x, y);
        initCar();
    }
    private void initCar() {
        loadImage("image/araba"+rnd+".png");
        getImageDimensions();
    }
    public void move() {
        y += dy;
    }

}
