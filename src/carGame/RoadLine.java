package carGame;

import java.util.Random;

public class RoadLine extends Sprite{
    private final int dy= Board.gameSpeed;
    public RoadLine(int x, int y) {
        super(x, y);
        initCar();
    }
    private void initCar() {
        loadImage("image/roadLine.png");
        getImageDimensions();
    }
    public void move() {
        y += dy;
    }
}
