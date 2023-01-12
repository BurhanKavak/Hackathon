package carGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    public static int gameSpeed = 1;
    private final int CAR_X = 150;
    private final int CAR_Y = 500;

    private Timer timer;

    private Car car;
    private Map map;
    private ArrayList<EnemyCar> enemyCars = new ArrayList<>();
    private ArrayList<Prize> prize = new ArrayList<>();

    private ArrayList<Heart> heartImg = new ArrayList<>();
    private ArrayList<Map> maps = new ArrayList<>();
    private ArrayList<RoadLine> roadLines = new ArrayList<>();

    private Random random = new Random();
    private Boolean ingame;
    private final int DELAY = 10;
    private int tmp = 10;
    private int sec;
   private int heart;
    private int currentMap = 1;

    private final int[][] pos = {
            {70, 50}, {60, 50}, {50, 50}

    };

    public Board() {

        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        ingame = true;
        map = new Map(0, 0, currentMap);

        car = new Car(CAR_X, CAR_Y);
        
        heart = 3;
        initHeart();


        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initHeart() {

        heartImg = new ArrayList<>();

        for (int[] p : pos) {
            heartImg.add(new Heart(p[0], p[1]));
        }
    }

    private void drawObject(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!maps.isEmpty()) {
            for (int i = maps.size() - 1; i >= 0; i--)
                if (maps.get(i).isVisible())
                    g2d.drawImage(maps.get(i).getImage(), maps.get(i).getX(), maps.get(i).getY(), this);
        }
        if (!roadLines.isEmpty()) {
            for (int i = roadLines.size() - 1; i >= 0; i--)
                if (roadLines.get(i).isVisible())
                    g2d.drawImage(roadLines.get(i).getImage(), roadLines.get(i).getX(), roadLines.get(i).getY(), this);
        }
        if (car.isVisible()) {
            g2d.drawImage(car.getImage(),
                    car.getX(), car.getY(), this);
        }

        if (!enemyCars.isEmpty()) {
            for (int i = enemyCars.size() - 1; i >= 0; i--)
                if (enemyCars.get(i).isVisible())
                    g2d.drawImage(enemyCars.get(i).getImage(), enemyCars.get(i).getX(), enemyCars.get(i).getY(), this);
        }

        if (!prize.isEmpty()) {
            for (int i = prize.size() - 1; i >= 0; i--)
                if (prize.get(i).isVisible())
                    g2d.drawImage(prize.get(i).getImage(), prize.get(i).getX(), prize.get(i).getY(), this);
        }

        for (Heart heart : heartImg) {
            if (heart.isVisible()) {
                g.drawImage(heart.getImage(), heart.getX(), heart.getY(), this);
            }
        }
        
        if (heart == 0) {
            gameOver(g);
            timer.stop();
        }


    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);
        setBackground(Color.BLACK);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (600 - metr.stringWidth(msg)) / 2, 800 / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        updateHeart();
        updateMyCar();
        updateRoadLine();
        updateEnemyCar();
        updatePrize();
        checkCollisions();
        checkCollisionWithPrize();
        sec();
        repaint();
    }

    private void updateEnemyCar() {
        if (tmp == 0 && sec % 2 == 0)
            enemyCars.add(new EnemyCar(random.nextInt(300) + 100, -200));
        if (!enemyCars.isEmpty()) {
            for (int i = enemyCars.size() - 1; i >= 0; i--) {
                enemyCars.get(i).move();
                if (enemyCars.get(i).getY() >= 800 || !enemyCars.get(i).isVisible())
                    enemyCars.remove(i);
            }
        }

    }

    private void updatePrize() {
        if (tmp == 0 && sec % 10 == 0)
            prize.add(new Prize(random.nextInt(300) + 100, -200));
        if (!prize.isEmpty()) {
            for (int i = prize.size() - 1; i >= 0; i--) {
                prize.get(i).move();
                if (prize.get(i).getY() >= 800 || !prize.get(i).isVisible())
                    prize.remove(i);
            }
        }

    }

    private void updateMap() {
        if (tmp == 10 && sec == 0)
            maps.add(new Map(0, 0, currentMap));
        if (sec / 15 == 0) {
            currentMap++;
            maps.add(new Map(0, -800, currentMap));
        }
    }

    private void updateRoadLine() {
        if (tmp == 0 && sec % 2 == 0)
            roadLines.add(new RoadLine(300, -200));
        if (!roadLines.isEmpty()) {
            for (int i = roadLines.size() - 1; i >= 0; i--) {
                roadLines.get(i).move();
                if (roadLines.get(i).getY() >= 800)
                    roadLines.remove(i);
            }
        }
        if (tmp == 10 && sec == 0) {
            for (int i = 600; i >= -200; i -= 200)
                roadLines.add(new RoadLine(300, i));
        }
    }

    private void updateMyCar() {
        if (car.isVisible()) {
            car.move();
        }
    }

    private void sec() {
        if (tmp < 1000)
            tmp += DELAY;
        else {
            tmp = 0;
            sec++;
        }
    }

    private void updateHeart() {

        if (heartImg.isEmpty()) {

            ingame = false;
            return;
        }


    }

    public void checkCollisions() {

        Rectangle r3 = car.getBounds();

        for (EnemyCar enemyCar : enemyCars) {

            Rectangle r2 = enemyCar.getBounds();

            if (r3.intersects(r2)) {


                for (int i = 0; i < heartImg.size() - 1; i++) {

                    heartImg.remove(i);

                }
                heart--;


                enemyCar.setVisible(false);
                ingame = false;
            }
        }
    }

    public void checkCollisionWithPrize() {

        Rectangle r3 = car.getBounds();

        for (Prize priz : prize) {

            Rectangle r2 = priz.getBounds();

            if (r3.intersects(r2)) {

                heart--;
                priz.setVisible(false);
                ingame = false;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObject(g);
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            car.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            car.keyPressed(e);
        }
    }
}
