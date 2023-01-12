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

    private final int CAR_X = 150;
    private final int CAR_Y = 500;

    private Timer timer;

    private Car car;
    private Map map;
    private ArrayList<EnemyCar> enemyCars = new ArrayList<>();
    private Random random = new Random();
    private Boolean ingame;
    private final int DELAY = 10;
    private int tmp=10;

    public Board() {

        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        ingame = true;
        map = new Map(0, 0);

        car = new Car(CAR_X, CAR_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void drawObject(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(map.getImage(), map.getX(), map.getY(), this);
        if (car.isVisible()) {
            g2d.drawImage(car.getImage(),
                    car.getX(), car.getY(), this);
        }
        if (!enemyCars.isEmpty()) {
            for (int i = enemyCars.size()-1; i >= 0; i--)
                if (enemyCars.get(i).isVisible())
                    g2d.drawImage(enemyCars.get(i).getImage(), enemyCars.get(i).getX(), enemyCars.get(i).getY(), this);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        updateMyCar();
        updateEnemyCar();
        sec();
        repaint();
    }

    private void updateEnemyCar() {
        if (tmp == 0)
            enemyCars.add(new EnemyCar(random.nextInt(200) + 200, -100));
        if (!enemyCars.isEmpty()) {
            for (int i = enemyCars.size()-1; i >= 0; i--) {
                enemyCars.get(i).move();
                System.out.println(enemyCars.size());
                if (enemyCars.get(i).getY() >= 800)
                    enemyCars.remove(i);
            }
        }

    }

    private void updateMyCar() {
        if (car.isVisible()) {
            car.move();
        }
    }

    private void sec() {
        if (tmp < 3000)
            tmp += DELAY;
        else {
            tmp = 0;
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
