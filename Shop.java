public class Shop {
    private Player player;
    private Handler handler;

    public Shop(Player player, Handler handler) {
        this.player = player;
        this.handler = handler;
    }

    // Method to display the shop menu
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 30));

        g.drawString("Shop", 350, 50);  // Display "Shop" title

        // Display items with prices
        g.drawString("Health Potion - $10", 300, 100);
        g.drawString("Damage Boost - $15", 300, 150);
        g.drawString("Speed Boost - $20", 300, 200);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Press 1 to Buy Health Potion", 250, 250);
        g.drawString("Press 2 to Buy Damage Boost", 250, 280);
        g.drawString("Press 3 to Buy Speed Boost", 250, 310);
    }

    // Method to process purchases
    public void buyItem(int itemId) {
        switch (itemId) {
            case 1: // Health Potion
                if (player.getScore() >= 10) {
                    player.decreaseScore(10);  // Deduct score
                    player.increaseHealth(20);  // Heal the player
                    System.out.println("Purchased Health Potion!");
                } else {
                    System.out.println("Not enough score to buy Health Potion.");
                }
                break;
            case 2: // Damage Boost
                if (player.getScore() >= 15) {
                    player.decreaseScore(15);  // Deduct score
                    player.increaseDamage(5);  // Increase damage
                    System.out.println("Purchased Damage Boost!");
                } else {
                    System.out.println("Not enough score to buy Damage Boost.");
                }
                break;
            case 3: // Speed Boost
                if (player.getScore() >= 20) {
                    player.decreaseScore(20);  // Deduct score
                    player.increaseSpeed(2);  // Increase speed
                    System.out.println("Purchased Speed Boost!");
                } else {
                    System.out.println("Not enough score to buy Speed Boost.");
                }
                break;
            default:
                System.out.println("Invalid item.");
                break;
        }
    }
}
