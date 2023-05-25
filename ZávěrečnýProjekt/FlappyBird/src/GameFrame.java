import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class GameFrame extends JFrame implements Runnable {

    private Player player = new Player(300, 300, 60, 58, 0, 2);

    private boolean isRunning = true;
    private boolean isSpacePressed = false;

    private BufferedImage backgroundImage;

    private List<Pipe> pipes = new LinkedList<>();

    private final int MAXCOUNTER = 350;
    private int counter = MAXCOUNTER;

    private int point = 0;


    private Random random = new Random();

    public GameFrame() throws HeadlessException {
        ImageIcon icon = new ImageIcon("src/Images/Ikona.jpg");
        setIconImage(icon.getImage());
        setVisible(true);
        addKeyListener(new Key());
        try {
            backgroundImage = ImageIO.read(new File("src/Images/Pozadi.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        pipes.add(new Pipe(510, 400, 78, 405, -1, 0, true));
        pipes.add(new Pipe(510, 0, 78, 200, -1, 0, false));
    }

    /**
     *Metoda která rozpohybovává hru
     */
    @Override
    public void run() {
        Instant lastFrameTime = Instant.now();
        while (isRunning == true) {
            Instant time = Instant.now();
            double deltaTime = Duration.between(lastFrameTime, time).toNanos() * 10E-10;
            double deltaWanted = 0.012;
            lastFrameTime = Instant.now();
            update();
            long msToSleep = (long) ((deltaWanted - deltaTime) * 1000);
            if (msToSleep > 0) {
                try {
                    Thread.sleep(msToSleep);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Metoda která obnovuje hru
     * Vytvoření nového obrázku ve kterém se vykresluje grafika
     * Vykreslení všech objektů, Zavoloní přepočtu všech souřadnic objektu
     * Generování nových pipe v určitém časovém intervalu
     * Odstranění pipe které jsou mimo obraz (Předchazení lagům)
     * Detekce nárazu hráče do pipe nebo do země
     */
    public void update() {
        Image image = createImage(getWidth(), getHeight());
        Graphics g = image.getGraphics();
        g.drawImage(backgroundImage, 0, 0, null);
        draw(g);
        getGraphics().drawImage(image, 0, 0, this);

        for (Pipe pipe : pipes) {
            pipe.update();
        }

        player.update(isSpacePressed);
        isSpacePressed = false;
        checkColision();

        if(counter==100){
            point++;
        }

        counter--;
        if(counter == 0){
            int defaultYTop = 400;
            int defaultHeightBottom = 200;
            int shift = random.nextInt(-200,200);

            defaultYTop+= shift;
            defaultHeightBottom+= shift;

            pipes.add(new Pipe(510, defaultYTop, 78, 405, -1, 0, true));
            pipes.add(new Pipe(510, 0, 78, defaultHeightBottom, -1, 0, false));
            counter= MAXCOUNTER;
        }

        for(int i = pipes.size()-1; i >=0; i--){
           Pipe pipe = pipes.get(i);
            if(pipe.x < -80){
                pipes.remove(i);
            }
        }
    }

    /**
     * Vykreslení pipe a hráče
     * @param g (objekt do kterého je vykreslováno)
     */
    public void draw(Graphics g) {
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
        player.draw(g);

    }

    /**
     * Vykreslení skore
     * @param g (objekt do kterého je vykreslováno)
     */
    private void drawScore(Graphics g){
      //  g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Font font = new Font("Serif", Font.PLAIN, 96);
        //g.setFont(font);
       // g.drawString(String.valueOf(point),30,30);
        }

    /**
     * Metoda na kontrolu kolizí
     * Když dojde ke kolizi s pipe nebo se zemi je hra ukončena
     */
    public void checkColision() {
        for (Pipe pipe : pipes) {
            if (player.intersects(pipe)) {
                isRunning = false;
                EndScreen endScreen = new EndScreen(this);
                endScreen.setLocationRelativeTo(this);
            }
        }

        if (player.y > 655) {
            isRunning = false;
            EndScreen endScreen = new EndScreen(this);
            endScreen.setLocationRelativeTo(this);
        }
    }

    /**
     * Objekt který naslouchá klávesnici
     */
    public class Key extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                isSpacePressed = true;
            }
        }
    }
}
