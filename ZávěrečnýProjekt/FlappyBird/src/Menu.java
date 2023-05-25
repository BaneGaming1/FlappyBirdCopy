import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {

        setTitle("Flappy Bird");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(510, 768);
       //setLocationRelativeTo(null);

        ImageIcon titleImage = new ImageIcon("src/Images/title.png");
        JLabel titleLabel = new JLabel(titleImage);
        titleLabel.setBounds(10, -250, getWidth(), getHeight());
        add(titleLabel);

        ImageIcon backgroundImage = new ImageIcon("src/Images/Pozadi.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);

        ImageIcon playButtonImage = new ImageIcon("src/Images/PlayButton.png");
        JButton playButton = new JButton(playButtonImage);
        playButton.setBounds(160, 450, 200, 111);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        add(playButton);

        ImageIcon exitButtonImage = new ImageIcon("src/Images/ExitButton.png");
        JButton exitButton = new JButton(exitButtonImage);
        exitButton.setBounds(0, 250, getWidth(), getHeight());
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        add(exitButton);

        /**
         * Metoda na spuštění hry
         */
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame();
                gameFrame.setSize(getWidth(), getHeight());
                gameFrame.setResizable(false);
                Thread t = new Thread(gameFrame);
                t.start();
            }
        });

        /**
         * Metoda která ukončí program
         */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             System.exit(0);
            }
        });

        /**
         * Umistěje grafické prvky v určitém pořadí
         */
        setComponentZOrder(titleLabel, 0);
        setComponentZOrder(playButton, 1);
        setComponentZOrder(exitButton, 2);
        setComponentZOrder(backgroundLabel, 3);
        setVisible(true);
        setResizable(false);
    }
}


