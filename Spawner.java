import java.util.Random;

public class Spawner {
    private Handler handler;
    private Player player;
    private Random rand = new Random();

    public Spawner(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
    }

    public void spawnZombie() {
        int spawnX = rand.nextInt(800);  // Random x position
        int spawnY = rand.nextInt(600);  // Random y position
        Zombie zombie = new Zombie(spawnX, spawnY, ID.Zombie, handler, player);
        handler.addObject(zombie);  // Add zombie to handler to be managed
    }

    public void startSpawning() {
        // Spawn a new zombie every 3 seconds
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);  // 3 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spawnZombie();
            }
        }).start();
    }
}
