import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.ArrayList;


public class Game extends Canvas implements Runnable, KeyListener {
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Player player;
    private Spawner spawner;

    public Game() {
        this.setPreferredSize(new Dimension(800, 600));
        handler = new Handler();
        player = new Player(400, 300, ID.Player, handler);
        spawner = new Spawner(handler, player);

        handler.addObject(player);
        this.addKeyListener(this);
        this.setFocusable(true);

        spawner.startSpawning();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000.0 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        int ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++;
                tick();
                delta--;
            }

            if (shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        handler.render(g);

        g.dispose();
        bs.show();
    }

    // KeyListener methods
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            player.setVelY(-5);  // Move player up
        }
        if (key == KeyEvent.VK_S) {
            player.setVelY(5);  // Move player down
        }
        if (key == KeyEvent.VK_A) {
            player.setVelX(-5);  // Move player left
        }
        if (key == KeyEvent.VK_D) {
            player.setVelX(5);  // Move player right
        }
        if (key == KeyEvent.VK_SPACE) {
        player.shoot();  // Trigger shooting action when spacebar is pressed
    }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            player.setVelY(0);  // Stop vertical movement
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            player.setVelX(0);  // Stop horizontal movement
        }
    }

    public void keyTyped(KeyEvent e) {
        // Not needed for this example
    }
}


