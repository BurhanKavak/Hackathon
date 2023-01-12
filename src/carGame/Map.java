package carGame;

public class Map extends Sprite {
    private final int dy= 1;
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
    public void move(){
        y+=dy* Board.gameSpeed;
    }
}
