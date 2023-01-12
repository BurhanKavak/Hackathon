package carGame;

import java.util.Random;

public class Decor extends Sprite{

    private final int dy = 2;

    Random random = new Random();

    int rnd = random.nextInt(5) + 1;

    public Decor(int x, int y) {
        super(x, y);
        initDecor();
    }

    private void initDecor() {
        loadImage("image/dekor" + rnd + ".png");
        getImageDimensions();
    }

    public void move() {
        y += dy * Board.gameSpeed;
    }
}
