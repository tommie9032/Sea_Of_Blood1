package main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
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

    // FPS
    int FPS = 60;

    KeyHandler key_h = new KeyHandler();
    Thread gameThread; // to create the existence of time in the game by drawing 60 fps
    // set player's default position
    int player_x = 100;
    int player_y = 100;
    int player_speed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key_h);
        this.setFocusable(true); //can be focused to receive input
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double draw_interval = (double) 1000000000 / FPS; //0.01666 seconds
        double delta = 0;
        long last_time = System.nanoTime();
        long current_time;
        long timer = 0;
        int draw_count = 0;

        while(gameThread != null) {
            current_time = System.nanoTime();
            delta += (current_time - last_time) / draw_interval;
            timer += current_time - last_time;
            last_time = current_time;

            if(delta>=1){
                update();
                repaint();
                delta--;
                draw_count++;

                if(timer >= 1000000000){
                    System.out.println("FPS: " + draw_count);
                    draw_count = 0;
                    timer = 0;
                }
            }

        }
    }

    public void update()
    {
        if(key_h.up_pressed){
            player_y -= player_speed;
        }
        else if (key_h.down_pressed) {
            player_y += player_speed;
        }
        else if (key_h.left_pressed) {
            player_x -= player_speed;
        }
        else if (key_h.right_pressed) {
            player_x += player_speed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);
        g2.fillRect(player_x,player_y,tile_size,tile_size);
        g2.dispose();
    }
}
