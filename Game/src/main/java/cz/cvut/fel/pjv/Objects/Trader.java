package cz.cvut.fel.pjv.Objects;

import cz.cvut.fel.pjv.framework.FileReader;
import cz.cvut.fel.pjv.framework.GameObject;
import cz.cvut.fel.pjv.framework.Image.IMG;
import cz.cvut.fel.pjv.framework.ObjectID;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * This Class is for creating trader
 */
public class Trader extends GameObject {

    static FileReader fileR;


    static {
        try {
            fileR = new FileReader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final float width = 64;
    private final float height = 64;


    /**
     * This function is used for getting parameters from the GameObjects.
     *
     * @param x  is the x cord. of the GameObject.
     * @param y  is the y cord. of the GameObject.
     * @param id is the id of the GameObject.
     */
    public Trader(float x, float y, ObjectID id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    /**
     * This function is for rendering trader
     * @param graphics is the name of the Graphics.
     */
    @Override
    public void render(Graphics graphics) {
        IMG loader = new IMG();
        BufferedImage trader_img = loader.loadImage("image/Hubby.png");
        graphics.drawImage(trader_img, (int) x, (int) y, (int) width, (int) height, null);
        graphics.drawString("Trader", (int) x + 10, (int) y);

    }

    /**
     * This function is used for creating bounds for player to step in so it can open the shop
     * @return Rectangle for the trader
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 10, (int) y + 5, (int) width - 25, (int) height - 10);

    }
}
