import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pipe extends Rectangle {



    private int vX;
    private int vY;
    private boolean isBottom;
    private BufferedImage pipe;
    private BufferedImage pipePart;
    private BufferedImage reversedPipe;

    public Pipe(int x, int y, int width, int height, int vX, int vY, boolean isBottom) {
        super(x, y, width, height);
        this.vX = vX;
        this.vY = vY;
        this.isBottom = isBottom;

        try {
            pipe = ImageIO.read(new File("src/Images/Pipe.png"));
            pipePart = ImageIO.read(new File("src/Images/pipe_part.png"));
            reversedPipe = ImageIO.read(new File("src/Images/ReversedPipe.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Přičtením rychlosti k souřadnicím pipe se pipe začne pohybovat
     */
    public void update() {
        x += vX;
        y += vY;
    }

    /**
     * Vykreslení pipe na vypočtených souřadnicích
     * @param g (objekt do kterého je vykreslováno)
     */
    public void draw(Graphics g) {
        if (isBottom) {
            int currentY = this.y + 119;
            g.drawImage(pipe, x, y, 78, 119, null);
            g.drawImage(pipePart, x, currentY, 78, 705 - currentY, null);
        }
        else {
            g.drawImage(reversedPipe, x, height - 119, 78, 119, null);
            g.drawImage(pipePart, x, 0, 78, height - 119, null);
        }
    }

    public int getvX() {
        return vX;
    }

    public void setvX(int vX) {
        this.vX = vX;
    }

    public int getvY() {
        return vY;
    }

    public void setvY(int vY) {
        this.vY = vY;
    }
}
