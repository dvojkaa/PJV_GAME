package cz.cvut.fel.pjv.Objects;

import cz.cvut.fel.pjv.framework.GameObject;
import cz.cvut.fel.pjv.framework.Image.IMG;
import cz.cvut.fel.pjv.framework.ObjectID;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *  This Class is for representing money
 */
public class Money extends GameObject {

    private final float width = 32;
    private final float height = 32;


    /**
     * This function is used for getting parameters from the GameObjects.
     *
     * @param x  is the x cord. of the GameObject.
     * @param y  is the y cord. of the GameObject.
     * @param id is the id of the GameObject.
     */
    public Money(float x, float y, ObjectID id) {
        super(x, y, id);
    }

    public void Collision(LinkedList<GameObject> object) {
        for (GameObject obj : object) {
            if (obj != null) {
                if (obj.getId() == ObjectID.Player) {
                    if (getBounds().intersects(obj.getBounds())) {
                        handler.removeObject(this);
                    }
                }
            }
        }
    }


    @Override
    public void tick(LinkedList<GameObject> object) {
        Collision(object);
    }


    /**
     * This function is for rendering the GameObject money
     * @param graphics is the name of the Graphics.
     */
    @Override
    public void render(Graphics graphics) {

        IMG loader = new IMG();
        BufferedImage money_img = loader.loadImage("image/Money.png");
        graphics.drawImage(money_img, (int) x, (int) y, (int) width, (int) height, null);

    }

    /**
     * This function is for creating Rectangle
     * @return Rectangle for hit-boxes for collecting
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }
}
