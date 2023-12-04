package cz.cvut.fel.pjv.framework;

import cz.cvut.fel.pjv.Game;
import cz.cvut.fel.pjv.Objects.Player;
import cz.cvut.fel.pjv.Objects.Weapon.Weapons;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static cz.cvut.fel.pjv.Game.handler;

/**
 * This Class is for saving game
 */
public class Save {
    WriterToFile fileW = new WriterToFile();

    /**
     * This function saves a map and calls for saving a player weapon and health
     */
    void saveLevel() {
        savePlayer();

        // saving img of level
        int pixel = 0x000000;
        int width = 512;
        int height = 512;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixel);
            }
        }
        for (GameObject object : handler.object) {

            if (object != null) {
                if (object.getId() == ObjectID.Block) {
                    pixel = 0xFFFFFF;
                } else if (object.getId() == ObjectID.Enemy) {
                    pixel = 0xFF0000;
                } else if (object.getId() == ObjectID.Player) {
                    pixel = 0x0000FF;
                } else if (object.getId() == ObjectID.Spear) {
                    pixel = 0x00FF00;
                } else if (object.getId() == ObjectID.Axe) {
                    pixel = 0xFFFF00;
                } else if (object.getId() == ObjectID.Money) {
                    pixel = 0x00FFFF;
                }else if (object.getId() == ObjectID.Trader) {
                    pixel = 0xFF00FF;
                }

                int x = object.getX() / 32;
                int y = object.getY() / 32;

                image.setRGB(x, y, pixel);
            }
        }

        File output = new File("data/config/safe.png");

        if (!output.getParentFile().exists()) {
            boolean created = output.getParentFile().mkdirs();
            if (!created) {
                System.err.println("Failed to create directory for saving the image.");
                return;
            }
        }
        try {

            // Write the image to the output file
            ImageIO.write(image, "png", output);

            System.out.println("Level saved to " + output.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving level: " + e.getMessage());
        }

    }

    /**
     * This function saves player attributes
     */
    private void savePlayer() {
        String newUroven = "uroven = " + Game.uroven;
        String newHearts = "hearts = " + Player.Hearts;
        String newSpeed = "speed = " + Player.Speed;
        String newMoney = "money = " + Player.Money;

        String newWeapon = "weapon = " + "Axe";
        if (Player.state == Weapons.Spear) {
            newWeapon = "weapon = " + "Spear";
        }

        try {
            fileW.WriteInventar(newHearts + "\n" + newWeapon + "\n" + newUroven + "\n" + newMoney + "\n" + newSpeed);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hearts and weapons saved");
    }

}

