package cz.cvut.fel.pjv.framework;

import cz.cvut.fel.pjv.Game;
import cz.cvut.fel.pjv.Objects.Player;
import cz.cvut.fel.pjv.Objects.Weapon.Axe_Weapon;
import cz.cvut.fel.pjv.Objects.Weapon.Spear_Weapon;
import cz.cvut.fel.pjv.Objects.Weapon.Weapons;
import cz.cvut.fel.pjv.window.Handler;
import cz.cvut.fel.pjv.window.State;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * This class is used for making inputs.
 */
public class Inputs extends KeyAdapter {
    Handler handler;
    FileReader file;

    {
        try {
            file = new FileReader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public int lastKey;
    long start, delay = 1000;

    public GameObject player;
    public int logger = file.getLogger();


    public Inputs(Handler handler) {
        this.handler = handler;
    }


    /**
     * This function is used for controlling which buttons were pressed and making the player move.
     *
     * @param e the event to be processed.
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject != null) {
                if (tempObject.getId() == ObjectID.Player && Game.state == State.Game) {
                    player = tempObject;
                    if (key == KeyEvent.VK_D) {
                        player.setVelocityX(Player.Speed);
                        lastKey = 3;
                    } else if (key == KeyEvent.VK_A) {
                        player.setVelocityX(-Player.Speed);
                        lastKey = 2;
                    } else if (key == KeyEvent.VK_W) {
                        player.setVelocityY(-Player.Speed);
                        lastKey = 0;
                    } else if (key == KeyEvent.VK_S) {
                        player.setVelocityY(Player.Speed);
                        lastKey = 1;
                    }
                }
            }
        }


        if (key == KeyEvent.VK_SPACE) {

            long end = System.currentTimeMillis();
            if ((end - start) > delay) {
                if(logger > 0) {
                    System.out.println(lastKey);
                }
                if (Player.state == Weapons.Axe) {
                    switch (lastKey) {
                        case 0 -> handler.addObject(new Axe_Weapon(player.x, player.y - 10, 0, handler, ObjectID.Axe));
                        case 1 ->
                                handler.addObject(new Axe_Weapon(player.x, player.y + 50, 180, handler, ObjectID.Axe));
                        case 2 ->
                                handler.addObject(new Axe_Weapon(player.x - 40, player.y + 20, 270, handler, ObjectID.Axe));
                        case 3 ->
                                handler.addObject(new Axe_Weapon(player.x + 40, player.y + 20, 90, handler, ObjectID.Axe));
                    }
                }

                if (Player.state == Weapons.Spear) {
                    switch (lastKey) {
                        case 0 ->
                                handler.addObject(new Spear_Weapon(player.x + 20, player.y - 40, 0, handler, ObjectID.Spear));
                        case 1 ->
                                handler.addObject(new Spear_Weapon(player.x, player.y + 50, 180, handler, ObjectID.Spear));
                        case 2 ->
                                handler.addObject(new Spear_Weapon(player.x - 20, player.y + 10, 270, handler, ObjectID.Spear));
                        case 3 ->
                                handler.addObject(new Spear_Weapon(player.x + 40, player.y + 10, 90, handler, ObjectID.Spear));
                    }
                }

                start = 0;
            }
        }
    }


    /**
     * This function is used for checking if the button was released.
     *
     * @param e the event to be processed.
     */
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        Iterator<GameObject> iterator = handler.object.iterator();
        while (iterator.hasNext()) {
            GameObject tempObject = iterator.next();

            if (tempObject != null) {
                if (tempObject.getId() == ObjectID.Player) {
                    if (key == KeyEvent.VK_D) tempObject.setVelocityX(0);
                    if (key == KeyEvent.VK_A) tempObject.setVelocityX(0);
                    if (key == KeyEvent.VK_W) tempObject.setVelocityY(0);
                    if (key == KeyEvent.VK_S) tempObject.setVelocityY(0);

                }

                if (tempObject.getId() == ObjectID.Axe) {
                    if (Player.state == Weapons.Axe) {
                        if (key == KeyEvent.VK_SPACE) {
                            iterator.remove();
                            start = System.currentTimeMillis();

                        }
                    }
                }
                if (tempObject.getId() == ObjectID.Spear) {
                    if (Player.state == Weapons.Spear) {
                        if (key == KeyEvent.VK_SPACE) {
                            iterator.remove();
                            start = System.currentTimeMillis();

                        }
                    }
                }
            }
        }
    }

}

