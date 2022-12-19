import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Scene extends JPanel implements ActionListener, KeyListener {

    private final int DELAY = 25;
    public static final int Tile_Length = 50;
    public static final int Scene_Length = 8;
    public static final int EnemyObjects = 5;
    private Timer timer;
    private Sprite player;
    private ArrayList<Game> enemies;

    class Play {

        private static void initWindow() {
            JFrame window = new JFrame("ANNIHILATE THE PAWNS!!!");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Scene canvas = new Scene();
            window.add(canvas);
            window.addKeyListener(canvas);
            window.setResizable(false);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    initWindow();
                }
            });
        }
    }

    public Scene() {
        setPreferredSize(new Dimension(Tile_Length * Scene_Length, Tile_Length * Scene_Length));
        setBackground(new Color(232, 232, 232));

        player = new Sprite();
        enemies = populateenemies();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.boundry();
        collision();
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        for (Game enemies : enemies) {
            enemies.draw(g, this);
        }
        player.draw(g, this);

        Toolkit.getDefaultToolkit().sync();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(214, 214, 214));
        for (int row = 0; row < Scene_Length; row++) {
            for (int col = 0; col < Scene_Length; col++) {
                if ((row + col) % 2 == 1) {
                    g.fillRect(
                        col * Tile_Length, 
                        row * Tile_Length, 
                        Tile_Length, 
                        Tile_Length
                    );
                }
            }    
        }
    }

    private ArrayList<Game> populateenemies() {
        ArrayList<Game> enemyList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < EnemyObjects; i++) {
            int enemyX = rand.nextInt(Scene_Length);
            int enemyY = rand.nextInt(Scene_Length);
            enemyList.add(new Game(enemyX, enemyY));
        }
        return enemyList;
    }

    private void collision() {
        ArrayList<Game> touchEnemy = new ArrayList<>();
        for (Game enemy : enemies) {
            if (player.getPos().equals(enemy.getPos())) {
                player.addScore(100);
                touchEnemy.add(enemy);
            }
        }
        enemies.removeAll(touchEnemy);
    }

}
