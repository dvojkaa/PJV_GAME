package cz.cvut.fel.pjv.Objects;

import cz.cvut.fel.pjv.Game;
import cz.cvut.fel.pjv.Objects.Weapon.Weapons;
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
 * This class is used for creating Player.
 */
public class Player extends GameObject {

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
    public static int Hearts = fileR.getHearts();
    public static int Speed = fileR.getSpeed();

    public static int Money = fileR.getMoney();

    public static Weapons state;


    /**
     * This function Player is for using whole class Player with his cord. x y and id.
     *
     * @param x  is x cord. of the Player.
     * @param y  is y cord. of the Player.
     * @param id is id for the GameObject.
     */
    public Player(float x, float y, ObjectID id) {
        super(x, y, id);


    }

    /**
     * This function Collision is used for the object Player to stop from going through the walls an Enemy.
     *
     * @param object is for checking for walls and Enemy.
     */
    public void Collision(LinkedList<GameObject> object) {

        Iterator<GameObject> iterator = object.iterator();
        while (iterator.hasNext()) {
            GameObject obj = iterator.next();
            if (obj != null) {
                if (obj.getId() == ObjectID.Enemy) {
                    if (getBounds().intersects(obj.getBounds())) {
                        x += velocityX * -1;
                        y += velocityY * -1;
                    }
                } else if (obj.getId() == ObjectID.Block) {
                    if (getBounds().intersects(obj.getBounds())) {
                        x += (velocityX * -1);
                        y += (velocityY * -1);
                    }
                } else if (obj.getId() == ObjectID.Spear) {
                    if (getBounds().intersects(obj.getBounds())) {
                        if (Player.state == Weapons.Axe) {
                            iterator.remove();
                        }
                        Player.state = Weapons.Spear;
                    }

                } else if (obj.getId() == ObjectID.Axe) {
                    if (getBounds().intersects(obj.getBounds())) {
                        if (Player.state == Weapons.Spear) {
                            iterator.remove();
                        }
                        Player.state = Weapons.Axe;
                    }

                } else if (obj.getId() == ObjectID.Money) {
                    if (getBounds().intersects(obj.getBounds())) {
                        Money++;
                        iterator.remove();
                    }
                } else if (obj.getId() == ObjectID.Trader) {
                    if (getBounds().intersects(obj.getBounds())) {
                        Game.state = State.Shop;
                        x += (velocityX * -1);
                        y += (velocityY * -1);
                    }
                }
            }
        }
    }


    /**
     * This function is called for every tick of the game and calls functions like Collision.
     *
     * @param object is used for the functions that we call.
     */
    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velocityX;
        y += velocityY;
        Collision(object);

    }

    /**
     * This function is used for rendering the Graphics of the Player.
     *
     * @param graphics is name for Graphics.
     */
    @Override
    public void render(Graphics graphics) {

        IMG loader = new IMG();

        BufferedImage player_img = loader.loadImage("image/Hubby.png");
        graphics.drawImage(player_img, (int) x, (int) y, (int) width, (int) height, null);


        BufferedImage heart_img = loader.loadImage("image/heart_img.png");
        for (int i = 1; i < Hearts + 1; i++)
            graphics.drawImage(heart_img, (int) (x - Game.WIDTH / 2 + 20 + (40 * i)), (int) y - Game.HEIGHT / 2 + 50, 40, 40, null);

        BufferedImage money_img = loader.loadImage("image/Money.png");
        graphics.drawImage(money_img, (int) (x - Game.WIDTH / 2 + 20 + (40 * (Hearts + 1))), (int) (y - Game.HEIGHT / 2 + 50), 32, 32, null);
        graphics.drawString(String.valueOf(Money), (int) x - Game.WIDTH / 2 + 20 + (40 * (Hearts + 1)) + 32, (int) y - Game.HEIGHT / 2 + 70);

// Testing Collisions
/*        Graphics2D g2d = (Graphics2D) graphics;
        graphics.setColor (Color.red);
        g2d.draw(getBounds ());
        g2d.draw(getBoundsR ());
        g2d.draw (getBoundsL ());
        g2d.draw(getBoundsT ());*/

    }

    /**
     * This function is for generating bounds for Collision.
     * @return new Rectangle for Collision.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 10, (int) y + 5, (int) width - 25, (int) height - 10);
    }


}
