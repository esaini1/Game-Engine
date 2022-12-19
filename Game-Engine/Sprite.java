import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {

    private BufferedImage image;
    private Point pos;
    private int score;

    public Sprite() {
        loadImage();
        pos = new Point(0, 0);
        score = 0;
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File("images/queen.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
            image, 
            pos.x * Scene.Tile_Length, 
            pos.y * Scene.Tile_Length, 
            observer
        );
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            pos.translate(0, -1);
        }
        if (key == KeyEvent.VK_RIGHT) {
            pos.translate(1, 0);
        }
        if (key == KeyEvent.VK_DOWN) {
            pos.translate(0, 1);
        }
        if (key == KeyEvent.VK_LEFT) {
            pos.translate(-1, 0);
        }
    }

    public void boundry() {

        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= Scene.Scene_Length) {
            pos.x = Scene.Scene_Length - 1;
        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= Scene.Scene_Length) {
            pos.y = Scene.Scene_Length - 1;
        }
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Point getPos() {
        return pos;
    }

}
