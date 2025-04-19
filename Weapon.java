public class Weapon {
    private String name;
    private int damage;
    private int bulletSpeed;
    private int fireRate; // Lower = faster firing

    public Weapon(String name, int damage, int bulletSpeed, int fireRate) {
        this.name = name;
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.fireRate = fireRate;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public int getFireRate() {
        return fireRate;
    }
}
