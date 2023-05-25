import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndScreen extends JFrame {


    private GameFrame gameFrame;


    public EndScreen(GameFrame gameFrame) {

        this.gameFrame = gameFrame;

        ImageIcon icon = new ImageIcon("src/Images/Ikona.jpg");
        setIconImage(icon.getImage());

        setTitle("Flappy Bird");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);

        ImageIcon gameOverImage = new ImageIcon("src/Images/gameOver.png");
        JLabel gameOverLabel = new JLabel(gameOverImage);
        gameOverLabel.setBounds(0, -140, getWidth(), getHeight());
        add(gameOverLabel);

        ImageIcon exitButtonImage = new ImageIcon("src/Images/ExitButton.png");
        JButton exitButton = new JButton(exitButtonImage);
        exitButton.setBounds(140, 250, 118, 50);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        add(exitButton);

        ImageIcon menuButtonImage = new ImageIcon("src/Images/menuButton.png");
        JButton menuButton = new JButton(menuButtonImage);
        menuButton.setBounds(135, 175, 118, 59);
        menuButton.setOpaque(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setBorderPainted(false);
        add(menuButton);

        ImageIcon backgroundImage = new ImageIcon("src/Images/ScreenColour.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getWidth());
        add(backgroundLabel);
        setVisible(true);


        /**
         * Button který ukončí program
         */

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         * Button krerý vrací hráče do menu
         */

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                gameFrame.dispose();
            }
        });






    }



}
