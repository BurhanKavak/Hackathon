package carGame;

public class Kupa extends Sprite{
    public Kupa(int x, int y) {
        super(x, y);
        initCar();
    }

    private void initCar() {
        loadImage("image/kupa.png");
        getImageDimensions();
    }
}
