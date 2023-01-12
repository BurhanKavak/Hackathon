package carGame;

public class Prize extends Sprite{

    public Prize(int x, int y) {
        super(x, y);
        initPrize();
    }

    private void initPrize() {
        loadImage("image/odul.png");
        getImageDimensions();
    }

    public void move() {
        int dy = 2;
        y += dy;
    }
}
