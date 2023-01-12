package carGame;

public class Heart extends Sprite{


    public Heart(int x, int y) {
        super(x, y);
        initHeart();
    }

    private void initHeart() {
        loadImage("image/cann.png");
        getImageDimensions();
    }
}
