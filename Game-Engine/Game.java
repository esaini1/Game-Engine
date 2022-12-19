import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Game {
    private BufferedImage image;
    private Point pos;
    public Game(int x, int y) {
        loadImage();
        pos = new Point(x, y);
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File("images/pawn.png"));
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
    public Point getPos() {
        return pos;
    }

}
