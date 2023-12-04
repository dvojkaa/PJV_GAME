package cz.cvut.fel.pjv.Objects.Weapon;

import cz.cvut.fel.pjv.Objects.Money;
import cz.cvut.fel.pjv.Objects.Player;
import cz.cvut.fel.pjv.framework.GameObject;
import cz.cvut.fel.pjv.framework.Image.IMG;
import cz.cvut.fel.pjv.framework.Image.IMG_Rotate;
import cz.cvut.fel.pjv.framework.ObjectID;
import cz.cvut.fel.pjv.window.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * This class is used for creating a spear weapon.
 */
public class Spear_Weapon extends GameObject {
    private final int rotation;
    private final Handler handler;

    /**
     * This function Spear_Weapon is for using whole
     * class Spear_Weapon with his cord. x y and rotation, handler and id.
     *
     * @param x        is x cord. of Weapon.
     * @param y        is y cord. of Weapon.
     * @param rotation is the rotation in which the Weapon should rotate.
     * @param handler  is handler of the game that handles all GameObjects.
     * @param id       is ID of the GameObject.
     */
    public Spear_Weapon(float x, float y, int rotation, Handler handler, ObjectID id) {
        super(x, y, id);
        this.handler = handler;
        this.rotation = rotation;
    }

    /**
     * This function Collision is used for the object Weapon, so it can detect when we hit Enemy.
     *
     * @param object is for checking for Enemy.
     */
    public void Collision(LinkedList<GameObject> object) {
        if (Player.state == Weapons.Spear) {
            for (int i = 0; i < object.size(); i++) {
                GameObject obj = object.get(i);
                if (obj != null) {
                    if (obj.getId() == ObjectID.Enemy) {
                        if (getBounds().intersects(obj.getBounds())) {
                            handler.addObject(new Money(obj.getX(), obj.getY(), ObjectID.Money));
                            handler.removeObject(obj);
                            numberOfEnemies--;
                        }
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
        Collision(object);
    }

    /**
     * This function is used for rendering the Graphics of the Weapon.
     *
     * @param graphics is name for Graphics.
     */
    @Override
    public void render(Graphics graphics) {
        IMG loader = new IMG();

        BufferedImage weapon_img = loader.loadImage("image/Chapadlo.png");
        BufferedImage rotateted_img = IMG_Rotate.rotate(weapon_img, rotation);

        graphics.drawImage(rotateted_img, (int) x, (int) y, 40, 64, null);
    }


    /**
     * This function is for generating bounds for Collision.
     *
     * @return new Rectangle for Collision.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 40, 64);
    }

}
