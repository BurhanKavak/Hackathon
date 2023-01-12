package carGame;

public class Map extends Sprite {
    public Map(int x, int y) {
        super(x, y);
        initCar();
    }

    private void initCar() {
        loadImage("image/yol1.png");
        getImageDimensions();
    }
}
