import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Menu menu = new Menu();
        menu.setVisible(true);

        ImageIcon icon = new ImageIcon("src/Images/Ikona.jpg");
        menu.setIconImage(icon.getImage());
    }
}