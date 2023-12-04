package cz.cvut.fel.pjv.window;

import cz.cvut.fel.pjv.framework.GameObject;

import java.awt.*;
import java.util.LinkedList;

/**
 * This class is used for creating handler for the GameObject.
 */
public class Handler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    private GameObject tempObject;

    /**
     * This function is called every tick of the game.
     */
    public void tick() {

        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            if (tempObject != null) {
                tempObject.tick(object);
            }
        }
    }

    /**
     * This function is used for rendering every object in the game.
     *
     * @param graphics is for the Graphics for the GameObject.
     */
    public void render(Graphics graphics) {

        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            if (tempObject != null) {
                tempObject.render(graphics);
            }
        }
    }

    /**
     * This function is used for adding objects to the game.
     *
     * @param object is the object that we will add.
     */
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    /**
     * This function is used for removing the objects from the game.
     *
     * @param object is the object that we will remove.
     */
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }


}
