package main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class GamePanel extends JPanel {
    // This inherits from JPanel to utilise all functionalities of  JPanel
    // This works as the game screen so we got to set SETTINGS

    //SCREEN SETTINGS
    final int original_tile_size = 16; // 16x16 tile

    final int scale = 3; //Modify this to increase or decrease the size of everything

    final int tile_size = original_tile_size * scale; // This will make it 48x48 tile in display

    final int max_screen_col = 16;
    final int max_screen_row = 12;

    final int screen_width = tile_size * max_screen_col; //768 pixels
    final int screen_height = tile_size * max_screen_row; // 576 pixels

    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }


}
