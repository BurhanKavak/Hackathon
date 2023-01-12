package carGame;

public class Map extends Sprite {
    private int dx=1*Board.gameSpeed;
    private int map;
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
