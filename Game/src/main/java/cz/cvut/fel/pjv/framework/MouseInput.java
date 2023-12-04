package cz.cvut.fel.pjv.framework;

import cz.cvut.fel.pjv.Game;
import cz.cvut.fel.pjv.Objects.Player;
import cz.cvut.fel.pjv.Objects.Weapon.Weapons;
import cz.cvut.fel.pjv.window.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

/**
 * This class is used for creating mouse inputs.
 */
public class MouseInput implements MouseListener {

    Save saveInstance = new Save();
    FileReader file;

    {
        try {
            file = new FileReader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * This function is used for checking which button was pressed in the menu.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
        //Menu Settings
        if (Game.state == State.Menu) {
            if (Game.WIDTH / 2 - 225 <= mX && mX <= Game.WIDTH / 2 - 50) {
                if (150 <= mY && mY <= 200) {
                    Game.state = State.Game;
                }
                if (250 <= mY && mY <= 300) {
                    Game.state = State.Settings;
                }
                if (350 <= mY && mY <= 400) {
                    Game.state = State.Quit;
                }
            } else if (Game.WIDTH / 2 + 75 <= mX && mX <= Game.WIDTH / 2 + 250) {
                if (150 <= mY && mY <= 200) {
                    //safe
                    //Call for saving
                    System.out.println("you clicked on safe");
                    saveInstance.saveLevel();
                }
                if (250 <= mY && mY <= 300) {
                    //load
                    //Call for loading
                    Game.Load();
                    Game.state = State.Game;
                }
                if (350 <= mY && mY <= 400) {
                    //new Game
                    Game.state = State.Reset;

                }
            } else if ((Game.WIDTH / 2 - 75 <= mX && mX <= Game.WIDTH / 2 + 105) && (Game.HEIGHT - 100 <= mY && mY <= Game.HEIGHT - 50)) {
                Game.state = State.Shop;
            }
        }
        //Settings Settings
        else if (Game.state == State.Settings) {
            if (Game.WIDTH / 2 - 225 <= mX && mX <= Game.WIDTH / 2 - 50) {
                if (150 <= mY && mY <= 200) {
                    Game.state = State.Menu;
                } else if (350 <= mY && mY <= 400) {
                    Game.state = State.Quit;
                }
            }
        }
        //Win
        else if (Game.state == State.Win) {
            if (Game.WIDTH / 2 - 225 <= mX && mX <= Game.WIDTH / 2 - 50) {
                if (150 <= mY && mY <= 200) {
                    Game.state = State.Reset;
                } else if (350 <= mY && mY <= 400) {
                    Game.state = State.Quit;
                }
            }

        }
        //GameOver
        else if (Game.state == State.GameOver) {
            if (Game.WIDTH / 2 - 225 <= mX && mX <= Game.WIDTH / 2 - 50) {
                if (150 <= mY && mY <= 200) {
                    Game.state = State.Reset;

                } else if (350 <= mY && mY <= 400) {
                    Game.state = State.Quit;
                }
            } else if (Game.WIDTH / 2 + 75 <= mX && mX <= Game.WIDTH / 2 + 250) {
                if (150 <= mY && mY <= 200) {
                    if (Player.Money >= 10) {
                        Player.Money = Player.Money - 10;
                        Player.Hearts = file.getHearts();
                        Game.state = State.Game;
                    }
                }
            }
        }
        //Shop
        else if (Game.state == State.Shop) {
            if (Game.WIDTH / 2 - 225 <= mX && mX <= Game.WIDTH / 2 - 50) {
                if (150 <= mY && mY <= 200) {
                    //Axe
                    Shop(1);
                }
                if (250 <= mY && mY <= 300) {
                    //Spear
                    Shop(2);
                }
            } else if (Game.WIDTH / 2 + 75 <= mX && mX <= Game.WIDTH / 2 + 250) {
                if (150 <= mY && mY <= 200) {
                    //Health + 1
                    Shop(3);
                }
                if (250 <= mY && mY <= 300) {
                    //Speed + 1
                    Shop(4);
                }
            } else if ((Game.WIDTH / 2 - 75 <= mX && mX <= Game.WIDTH / 2 + 105) && (Game.HEIGHT - 100 <= mY && mY <= Game.HEIGHT - 50)) {
                Game.state = State.Game;
            }
        }

    }


    /**
     * This function is for getting items from shop
     *
     * @param Item is number of items
     */
    public static void Shop(int Item) {
        switch (Item) {
            case 1 -> {
                if (Player.Money >= 5) {
                    Player.Money = Player.Money - 5;
                    Player.state = Weapons.Axe;
                }
            }
            case 2 -> {
                if (Player.Money >= 10) {
                    Player.Money = Player.Money - 10;
                    Player.state = Weapons.Spear;
                }
            }
            case 3 -> {
                if (Player.Money >= 5) {
                    Player.Money = Player.Money - 5;
                    Player.Hearts++;
                }
            }
            case 4 -> {
                if (Player.Money >= 10) {
                    Player.Money = Player.Money - 10;
                    Player.Speed++;
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
