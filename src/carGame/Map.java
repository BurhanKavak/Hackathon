package carGame;

public class Map extends Sprite {
    private final int dx= Board.gameSpeed;
    private final int map;
    public Map(int x, int y,int map) {
        super(x, y);
        this.map=map;
        initCar();
    }

    private void initCar() {
        loadImage("image/yol"+map+".png");
        getImageDimensions();
    }
    private void move(){
        x+=dx;
    }
}
