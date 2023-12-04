package cz.cvut.fel.pjv.Objects;

import cz.cvut.fel.pjv.framework.GameObject;
import cz.cvut.fel.pjv.framework.ObjectID;

import java.awt.*;
import java.util.LinkedList;

/**
 * This class is used for creating wall.
 */
public class Block extends GameObject {
    private final float width = 32;
    private final float height = 32;


    /**
     * This function Block is for using whole class Block
     * with his cord. x y and id.
     *
     * @param x  is x cord. of the Block.
     * @param y  is y cord. of the Block.
     * @param id is id of the Block.
     */
    public Block(float x, float y, ObjectID id) {
        super(x, y, id);
    }


    /**
     * This function isn't used, because Blocks don't move.
     *
     * @param object is the GameObject.
     */
    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    /**
     * This function is used for rendering the Block in the Game.
     *
     * @param graphics is name for Graphics.
     */
    @Override
    public void render(Graphics graphics) {

        graphics.setColor(Color.BLACK);
        graphics.fillRect((int) x, (int) y, (int) width, (int) height);

    }

    /**
     * This function is used for creating bound for the Block.
     *
     * @return new rectangle for the Block.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }


}
