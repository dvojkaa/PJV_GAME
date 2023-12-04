package cz.cvut.fel.pjv.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used for reading files.
 */
public class FileReader {

    Scanner scan2;
    Scanner scan;

    /**
     * Attributes for the game


     * Player
     */
    private int speed;
    private int hearts;
    private int money;
    private String weapon;

    /**
     * Enemy
     */
    private float area;
    private float speedE;

    /**
     * Level
     */
    private int uroven;
    private int logger;


    /**
     * This function is used for getting the file.
     * @throws FileNotFoundException if there are some problems.
     */


    public FileReader() throws FileNotFoundException {
        File configFile = new File("data/config/ConfigFile.txt");
        if (configFile.exists()) {
            scan = new Scanner(configFile);
            readFile();
        } else {
            throw new FileNotFoundException("ConfigFile.txt not found");
        }

        File inventoryFile = new File("data/config/Inventory.txt");
        if (inventoryFile.exists()) {
            scan2 = new Scanner(inventoryFile);
        } else {
            throw new FileNotFoundException("Inventory.txt not found");
        }
    }



    /**
     * This function is used for reading the file and converting txt to parameters.
     */
    public void readFile() {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split("=");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                switch (key) {
                    case "speed" -> speed = Integer.parseInt(value);
                    case "hearts" -> hearts = Integer.parseInt(value);
                    case "money" -> money = Integer.parseInt(value);
                    case "triggerArea" -> area = Float.parseFloat(value);
                    case "speedEnemy" -> speedE = Float.parseFloat(value);
                    case "weapon" -> weapon = (value);
                    case "logger" -> logger = Integer.parseInt(value);
                }
            }
        }
    }

    /**
     * This function is used for reading the file and converting txt to parameters.
     */
    public void readInventar(){
        while (scan2.hasNextLine()) {
            String line = scan2.nextLine();
            String[] parts = line.split("=");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                switch (key) {
                    case "weapon" -> weapon = (value);
                    case "uroven" -> uroven = Integer.parseInt((value));
                    case "hearts" -> hearts = Integer.parseInt((value));
                    case "money" -> money = Integer.parseInt(value);
                    case "speed" -> speed = Integer.parseInt(value);
                }
            }
        }
    }
    /**
     * This function is used for getting speed for the Player.
     * @return the speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * This function is used for getting trigger area for the Enemy.
     * @return the area.
     */
    public float getArea() {
        return area;
    }

    /**
     * This function is used for getting speed for the Enemy.
     * @return the speed
     */
    public float getSpeedE() {
        return speedE;
    }

    /**
     * This function is used for getting hearts for the Player.
     * @return the hearts.
     */
    public int getHearts() {
        return hearts;
    }

    /**
     * This function is used for getting a weapon for the Player.
     * @return the weapon
     */
    public String getWeapon() {
        return weapon;
    }

    /**
     * This function is used for getting level for the map.
     * @return the uroven
     */
    public int getUroven(){return uroven;}

    /**
     * This function is used for getting money for the Player.
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * This function is used for getting logger for the Settings.
     * @return the logger
     */
    public int getLogger() {
        return logger;
    }
}
