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
    public static int gameSpeed=1;
    private final int CAR_X = 150;
    private final int CAR_Y = 500;

    private Timer timer;

    private Car car;
    private ArrayList<EnemyCar> enemyCars = new ArrayList<>();
    private ArrayList<Map> maps = new ArrayList<>();
    private ArrayList<RoadLine> roadLines = new ArrayList<>();
    private Random random = new Random();
    private Boolean ingame;
    private final int DELAY = 10;
    private int tmp = 10;
    private int sec;
    private int heart=3;
    private int currentMap=1;

    public Board() {

        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        ingame = true;
        car = new Car(CAR_X, CAR_Y);
        timer = new Timer(DELAY, this);
        timer.start();
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


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        updateMyCar();
        updateRoadLine();
        updateEnemyCar();
        checkCollisions();
        sec();
        repaint();
    }

    private void updateEnemyCar() {
        if (tmp == 0 && sec % 4 == 0)
            enemyCars.add(new EnemyCar(random.nextInt(300) + 100, -200));
        if (!enemyCars.isEmpty()) {
            for (int i = enemyCars.size() - 1; i >= 0; i--) {
                enemyCars.get(i).move();
                if (enemyCars.get(i).getY() >= 800|| !enemyCars.get(i).isVisible())
                    enemyCars.remove(i);
            }
        }

    }
    private void updateMap() {
        if (tmp == 10 && sec == 0)
            maps.add(new Map(0, 0,currentMap));
        if (sec/15 == 0) {
            currentMap++;
            maps.add( new Map(0, -800, currentMap));
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

    public void checkCollisions() {

        Rectangle r3 = car.getBounds();

        for (EnemyCar enemyCar : enemyCars) {

            Rectangle r2 = enemyCar.getBounds();

            if (r3.intersects(r2)) {

                heart--;
                enemyCar.setVisible(false);
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
