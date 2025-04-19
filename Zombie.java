import java.awt.*;

public class Zombie extends GameObject {
    private Handler handler;
    private Player player;

    public Zombie(int x, int y, ID id, Handler handler, Player player) {
        super(x, y, id);
        this.handler = handler;
        this.player = player;
    }

    public void tick() {
        // Move toward player
        if (x < player.getX()) {
            x += 2;  // Move right
        } else if (x > player.getX()) {
            x -= 2;  // Move left
        }

        if (y < player.getY()) {
            y += 2;  // Move down
        } else if (y > player.getY()) {
            y -= 2;  // Move up
        }

        // Collision detection with player (if necessary)
        if (getBounds().intersects(player.getBounds())) {
            // Handle player-zombie collision (e.g., reduce player health)
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 32, 32);  // Draw zombie as a blue square
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);  // Zombie's bounds for collision detection
    }
}

