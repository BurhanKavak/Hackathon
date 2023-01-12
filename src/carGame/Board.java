package carGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private final int CAR_X = 150;
    private final int CAR_Y = 500;

    private Timer timer;

    private Car car;

    private Boolean ingame;

    private final int DELAY = 10;

    public Board() {

        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        ingame = true;

        car = new Car(CAR_X,CAR_Y);

        timer = new Timer(DELAY,this);
        timer.start();
    }

    private void drawObject(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (car.isVisible()) {
            g2d.drawImage(car.getImage(),
                    car.getX(), car.getY(), this);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        updateMyCar();
        repaint();
    }

    private void updateMyCar() {
        if(car.isVisible()){
            car.move();
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
