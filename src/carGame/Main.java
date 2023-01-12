package carGame;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setSize(600,800);
        setResizable(false);
        setTitle("Car Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
