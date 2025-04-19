import java.awt.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;


public class Handler {
    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();

    public List<GameObject> getObjects() {
    return objects;
}


    public void tick() {
        for (GameObject object : objects) {
            object.tick();
        }
    }

    public void render(Graphics g) {
        for (GameObject object : objects) {
            object.render(g);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    public boolean isKeyPressed(int keyCode) {
        // To handle key input, you will need a KeyListener for game actions
        return false;
    }

    public void clearObjects() {
    objects.clear();
}
}

