package carGame;

public class Prize extends Sprite{

    private int dy = 2;

    public Prize(int x, int y) {
        super(x, y);
        initPrize();
    }

    private void initPrize() {
        loadImage("image/odul.png");
        getImageDimensions();
    }

    public void move() {
        y += dy;
    }
}
