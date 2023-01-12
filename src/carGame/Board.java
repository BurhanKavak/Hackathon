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

    private int score;
    private Map map;
    private ArrayList<EnemyCar> enemyCars = new ArrayList<>();
    private ArrayList<Prize> prize = new ArrayList<>();
    private ArrayList<Decor> decors = new ArrayList<>();

    private ArrayList<Heart> heartImg = new ArrayList<>();
    private ArrayList<Map> maps = new ArrayList<>();
    private ArrayList<RoadLine> roadLines = new ArrayList<>();

    private Random random = new Random();
    private Boolean ingame;
    private Boolean pause = false;
    private final int DELAY = 10;
    private int tmp = 10;
    private int sec;
    private int heart;
    private int currentMap = 0;
    private FileManagement fm = new FileManagement();
    private int maxScore = Integer.parseInt(fm.readFile());

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
        for (int i = maps.size() - 1; i >= 0; i--)
            if (maps.get(i).isVisible())
                g2d.drawImage(maps.get(i).getImage(), maps.get(i).getX(), maps.get(i).getY(), this);
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

        if (!decors.isEmpty()) {
            for (int i = decors.size() - 1; i >= 0; i--)
                if (decors.get(i).isVisible())
                    g2d.drawImage(decors.get(i).getImage(), decors.get(i).getX(), decors.get(i).getY(), this);
        }

        if (heart == 0) {
            gameOver(g);
            timer.stop();
        }
        g2d.setColor(Color.white);
        g2d.drawString("SKOR = " + score + "",
                400, 50);


    }

    private void gameOver(Graphics g) {
        String msg = "Maalesef kaybettiniz";
        if (maxScore < score) {
            fm.writeFile(String.valueOf(score));
            msg = "Tebrikler rekor kırdınız";
        }


        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);
        setBackground(Color.BLACK);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (600 - metr.stringWidth(msg)) / 2, 800 / 2);
        if (maxScore < score){
            g.drawString("En yüksek score= " + score, (600 - metr.stringWidth(msg)) / 2, 800 / 2 + 100);
            Kupa kupa=new Kupa(180,100);
            g.drawImage(kupa.image,kupa.getX(),kupa.getY(),this);
        }
        else{
            g.drawString("En yüksek score= " + maxScore, (600 - metr.stringWidth(msg)) / 2, 800 / 2 + 100);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!pause) {
            addMap();
            updateMap();
            updateDecor();
            updateHeart();
            updateMyCar();
            updateRoadLine();
            updateScore();
            updateEnemyCar();
            updatePrize();
            checkCollisions();
            checkCollisionWithPrize();
            sec();
            repaint();

        }

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

    private void updateDecor() {
        if (tmp == 0 && sec % 2 == 0) {
            decors.add(new Decor(0, -200));
            decors.add(new Decor(520, -200));
        }

        if (!decors.isEmpty()) {
            for (int i = decors.size() - 1; i >= 0; i--) {
                decors.get(i).move();
                if (decors.get(i).getY() >= 800 || !decors.get(i).isVisible())
                    decors.remove(i);
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
        if (maps.size() == 2) {
            for (int i = maps.size() - 1; i >= 0; i--) {
                maps.get(i).move();
                if (maps.get(i).getY() >= 800)
                    maps.remove(i);
            }
        }

    }

    private void addMap() {
        if (currentMap == 0) {
            currentMap++;
            maps.add(new Map(0, 0, currentMap));
        } else if (tmp == 0 && sec % 15 == 0 && currentMap < 3) {
            currentMap++;
            maps.add(new Map(0, -800, currentMap));
            gameSpeed++;
        }
    }

    public void updateScore() {
        if (tmp == 0 && sec % 2 == 0) {
            score += 2;
        }
    }

    private void updateRoadLine() {
        if (tmp == 0 && sec % 1 == 0)
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
                priz.setVisible(false);
                score += 10;
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
            int key;
            key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE) {
                pause = !pause;
            }


            car.keyPressed(e);
        }
    }
}
