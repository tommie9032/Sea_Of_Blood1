import java.awt.*;
import java.util.List;

public class Player extends GameObject {
    private Handler handler;
    private boolean shooting = false;
    private int health = 100;
    private int score = 0;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        // Update player position based on velocity
        x += velX;
        y += velY;

        // Prevent player from moving outside the window
        if (x <= 0) x = 0;
        if (x >= 768) x = 768;  // Screen width - player width
        if (y <= 0) y = 0;
        if (y >= 568) y = 568;  // Screen height - player height

        // Shoot a bullet if the spacebar is pressed
        if (shooting) {
            shootBullet();
            shooting = false; // Reset shooting status after firing one bullet
			}

	     for (GameObject tempObject : handler.getObjects()) {
            if (tempObject.getId() == ID.Zombie) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    health -= 10;  // Reduce health when colliding with a zombie
                    handler.removeObject(tempObject);  // Remove the zombie
                    break;  // Stop checking after collision
                }
            }
        }
	     if (health <= 0) {
            // Game Over logic (e.g., end the game)
            System.out.println("Game Over!");
        }
        
    }

    private void shootBullet() {
        int bulletX = x + 16;  // Spawn bullet slightly in front of player
        int bulletY = y + 16;  // Center bullet with player
        int bulletVelX = 0;  // Bullet will move horizontally or vertically
        int bulletVelY = 0;

        // Shoot based on the direction of movement
        if (velX > 0) {
            bulletVelX = 5;  // Shoot right
        } else if (velX < 0) {
            bulletVelX = -5;  // Shoot left
        } else if (velY > 0) {
            bulletVelY = 5;  // Shoot down
        } else if (velY < 0) {
            bulletVelY = -5;  // Shoot up
        }

        Bullet bullet = new Bullet(bulletX, bulletY, ID.Bullet, handler, bulletVelX, bulletVelY);
        handler.addObject(bullet);  // Add bullet to handler to be managed
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 32, 32);  // Draw player as a green square
         g.setColor(Color.white);
        g.drawString("Health: " + health, 10, 10);
    }
   
   public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);  // Player's bounds for collision detection
    }

    // Method to handle shooting when spacebar is pressed
    public void shoot() {
        shooting = true;  // Set shooting flag to true when spacebar is pressed
    }
}





