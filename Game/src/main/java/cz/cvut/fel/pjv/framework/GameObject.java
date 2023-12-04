package cz.cvut.fel.pjv.framework;

import cz.cvut.fel.pjv.Game;

import java.awt.*;
import java.util.LinkedList;

/**
 * This abstract class is used for creating a platform that I can build the objects.
 */
public abstract class GameObject extends Game {


    protected float y;
    protected float x;

    protected float velocityX, velocityY;
    protected ObjectID id;

    /**
     * This function is used for getting parameters from the GameObjects.
     *
     * @param x  is the x cord. of the GameObject.
     * @param y  is the y cord. of the GameObject.
     * @param id is the id of the GameObject.
     */
    public GameObject(float x, float y, ObjectID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /**
     * This function is used for ticking the GameObject.
     *
     * @param object is the GameObject.
     */
    public abstract void tick(LinkedList<GameObject> object);

    /**
     * This function is used for rendering the GameObject.
     *
     * @param graphics is the name of the Graphics.
     */
    public abstract void render(Graphics graphics);

    /**
     * This function is used for creating bounds for the GameObject.
     *
     * @return new rectangle.
     */
    public abstract Rectangle getBounds();


    /**
     * This function is used for getting x of the GameObject.
     *
     * @return x of the GameObject.
     */
    public int getX() {
        return (int) x;
    }

    /**
     * This function is used for getting y of the GameObject.
     *
     * @return y of the GameObject.
     */
    public int getY() {
        return (int) y;
    }

    /**
     * This function sets X of the GameObject
     * @param xx is given X
     */
    public void setX(int xx) {
        x = xx;
    }

    /**
     * This function sets Y of the GameObject
     * @param yy is given Y
     */
    public void setY(int yy) {
        y = yy;
    }


    /**
     * This function is used for setting velocity x of the GameObject.
     *
     * @param velocityX is the speed of the x in which the GameObject will move.
     */
    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * This function is used for setting velocity y of the GameObject.
     *
     * @param velocityY is the speed of the y in which the GameObject will move.
     */
    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * This function is used for getting id of the GameObject.
     *
     * @return id of the GameObject
     */
    public ObjectID getId() {
        return id;
    }

}
