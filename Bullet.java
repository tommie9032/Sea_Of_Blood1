import java.awt.*;

public class Bullet extends GameObject {
    private Handler handler;


    public Bullet(int x, int y, ID id, Handler handler, int velX, int velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        
    }

    public void tick() {
        // Update bullet's position based on velocity
        x += velX;
        y += velY;

        // Remove the bullet if it goes out of bounds
        if (x < 0 || x > 800 || y < 0 || y > 600) {
            handler.removeObject(this);
        }

        // Collision detection with zombies
        for (GameObject tempObject : handler.getObjects()) {
            if (tempObject.getId() == ID.Zombie) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);  // Remove bullet
                    handler.removeObject(tempObject);  // Remove zombie
   		    
                    break;  // Stop checking after collision
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 10, 10);  // Draw bullet as a red square
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);  // Bullet's bounds for collision detection
    }
}

