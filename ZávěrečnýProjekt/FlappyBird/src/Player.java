import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Rectangle {

    private int vX;
    private int vY;
    private int extraVY = 0;

    private BufferedImage bird;

    public Player(int x, int y, int width, int height, int vX, int vY) {
        super(x, y, width, height);
        this.vX = vX;
        this.vY = vY;

        try {
            bird = ImageIO.read(new File("src/Images/Bird60x58.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Přičtením rychlosti k souřadnicím hráče se hráč začne pohybovat
     * Pokud je detekován stisk mezerníku je dočasně nastavena rychlost y čímž dojde k poskočení hráče nahoru
     * @param isSpacePressed (informace zda byl detekován stisk mezerníku)
     */
    public void update(boolean isSpacePressed) {
        if (isSpacePressed == true) {
            extraVY = 14;
        }
        x += vX;
        y += vY - extraVY;

        if (extraVY > 0) {
            extraVY--;
        }
    }

    /**
     * Vykreslení hráče na vypočtených souřadnicích
     * @param g (objekt do kterého je vykreslováno)
     */
    public void draw(Graphics g) {
        g.drawImage(bird, x, y, 60, 58, null);
    }
}
