package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();  // to create a window we need to use JFrame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this lets window close when clicked "X" mark
        window.setResizable(false);
        window.setTitle("Sea Of Bones"); // Title in the panel

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // causes window to fit preferred size given in GamePanel Class
        window.setLocationRelativeTo(null);
        // since we didn't specify the location of window, it will simply display in center
        window.setVisible(true); // to see the window
    }
}