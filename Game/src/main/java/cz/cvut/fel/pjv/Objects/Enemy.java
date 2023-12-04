package cz.cvut.fel.pjv.Objects;

import cz.cvut.fel.pjv.Game;
import cz.cvut.fel.pjv.framework.FileReader;
import cz.cvut.fel.pjv.framework.GameObject;
import cz.cvut.fel.pjv.framework.Image.IMG;
import cz.cvut.fel.pjv.framework.ObjectID;
import cz.cvut.fel.pjv.window.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class is used for creating Enemy.
 */
public class Enemy extends GameObject {

    FileReader file;

    {
        try {
            file = new FileReader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    long start, delay = 200;
    private final float width = 64;
    private final float height = 64;
    private float area = file.getArea();
    private final float speed = file.getSpeedE();

    /**
     * This function Enemy is for using whole class Enemy with his cord. x y and id.
     *
     * @param x  is x cord. of Enemy.
     * @param y  is y cord. of Enemy.
     * @param id is id for the GameObject.
     */
    public Enemy(float x, float y, ObjectID id) {
        super(x, y, id);

    }

    /**
     * This function Movement makes the object move the right way to the object Player
     *
     * @param object is used for checking for a Player object
     */
    private void Movement(LinkedList<GameObject> object) {
        float tmp_area = area;
        for (GameObject tempObject : object) {
            if (tempObject != null) {
                if (tempObject.getId() == ObjectID.Player && Game.state == State.Game) {
                    if (area > (tempObject.getX() - x) && (tempObject.getX() - x) > -area && area > (tempObject.getY() - y) && (tempObject.getY() - y) > -area) {
                        if (x < tempObject.getX()) {
                            setVelocityX(speed);
                        }
                        if (x > tempObject.getX()) {
                            setVelocityX(-speed);
                        }
                        if (y > tempObject.getY()) {
                            setVelocityY(-speed);
                        }
                        if (y < tempObject.getY()) {
                            setVelocityY(speed);
                        }

                        area++;
                    } else {
                        setVelocityX(0);
                        setVelocityY(0);

                        if (area > tmp_area * 4) {
                            area = tmp_area * 4;
                        }
                    }

                } else if (Game.state == State.Menu) {
                    setVelocityX(0);
                    setVelocityY(0);

                }
            }
        }

    }


    /**
     * This function Collision is used for the object Enemy to stop from going through the walls a Player
     *
     * @param object is for checking for walls and Player
     */
    public void Collision(LinkedList<GameObject> object) {
        Iterator<GameObject> iterator = object.iterator();
        while (iterator.hasNext()) {
            GameObject obj = iterator.next();
            if (obj != null) {
                if (obj.getId() == ObjectID.Player) {
                    if (getBounds().intersects(obj.getBounds())) {
                        long end = System.currentTimeMillis();
                        if ((end - start) > delay) {
                            Player.Hearts--;
                            start = 0;
                            if (Player.Hearts <= 0) {
                                Game.state = State.GameOver;
                            }
                        }
                        x += velocityX * -1;
                        y += velocityY * -1;
                        start = System.currentTimeMillis();
                    }
                }
                if (obj.getId() == ObjectID.Enemy) {
                    if (obj != this) {
                        if (getBounds().intersects(obj.getBounds())) {
                            x += velocityX * -1;
                            y += velocityY * -1;
                        }
                    }
                }
                if (obj.getId() == ObjectID.Block) {
                    if (getBounds().intersects(obj.getBounds())) {
                        x += (velocityX * -1);
                        y += (velocityY * -1);
                    }
                }
            }
        }
    }


    /**
     * This function is called for every tick of the game and calls functions like Collision and Movement.
     *
     * @param object is used for the functions that we call.
     */
    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velocityX;
        y += velocityY;
        Collision(object);
        Movement(object);
    }

    /**
     * This function is used for rendering the Graphics of the Enemy.
     *
     * @param graphics is name for Graphics
     */
    @Override
    public void render(Graphics graphics) {

        IMG loader = new IMG();
        BufferedImage enemy_img = loader.loadImage("image/Hushhush.png");

        graphics.drawImage(enemy_img, (int) x, (int) y, (int) width, (int) height, null);


    }

    /**
     * This function is for generating bounds for Collision.
     *
     * @return new Rectangle for Collision
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 10, (int) y + 5, (int) width - 25, (int) height - 10);
    }

}

