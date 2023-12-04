package cz.cvut.fel.pjv.window;

import cz.cvut.fel.pjv.Game;
import cz.cvut.fel.pjv.Objects.Player;
import cz.cvut.fel.pjv.framework.Image.IMG;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is used for creating a menu.
 */
public class Menu {


    /**
     * This function is used for rendering Menu.
     *
     * @param graphics   is name for Graphics.
     * @param graphics2D is name for Graphics2D.
     */
    public void render(Graphics graphics, Graphics2D graphics2D) {
        Font fntO = new Font("arial", Font.BOLD, 50);
        Font fnt1 = new Font("arial", Font.BOLD, 25);
        IMG loader = new IMG();
        BufferedImage player_img = loader.loadImage("image/Background_menu.png");
        if (Game.state != State.Game) {
            graphics.drawImage(player_img, 0, 0, Game.WIDTH, Game.HEIGHT, null);
            //System.out.println(Game.state);
        }
        if (Game.state == State.Menu) {

            graphics.setFont(fntO);
            graphics.setColor(Color.BLACK);


            graphics2D.fill(Save());
            graphics2D.fill(Play());
            graphics2D.fill(Settings());
            graphics2D.fill(Load());
            graphics2D.fill(Quit());
            graphics2D.fill(NWGame());
            graphics2D.fill(Shop());

            graphics.setColor(Color.WHITE);
            graphics.drawString("Habbis Adventure", Game.HEIGHT / 2 - fntO.getSize() * 2, 100);
            graphics.setFont(fnt1);

            graphics.drawString("Play", Play().x + 19, Play().y + 30);
            graphics.drawString("Save", Save().x + 19, Save().y + 30);
            graphics.drawString("How to Play", Settings().x + 19, Settings().y + 30);
            graphics.drawString("Load", Load().x + 19, Load().y + 30);
            graphics.drawString("Quit", Quit().x + 19, Quit().y + 30);
            graphics.drawString("New Game", NWGame().x + 19, NWGame().y + 30);
        } else if (Game.state == State.Settings) {
            graphics.setFont(fntO);
            graphics.setColor(Color.BLACK);
            graphics2D.fill(Quit());
            graphics2D.fill(Play());

            //Settings
            graphics.setColor(Color.WHITE);
            graphics.drawString("Habbis Adventure", Game.HEIGHT / 2 - fntO.getSize() * 2, 100);
            graphics.setFont(fnt1);
            graphics.drawString("Go back", Play().x + 19, Play().y + 30);
            graphics.drawString("WASD = Movement \n", Settings().x + 19, Settings().y);
            graphics.drawString("SPACE = Attack \n", Settings().x + 19, Settings().y + 25);
            graphics.drawString("R = Reset", Settings().x + 19, Settings().y + 50);
            graphics.drawString("ESC = Pause/Quit", Settings().x + 19, Settings().y + 75);
            graphics.drawString("Quit", Quit().x + 19, Quit().y + 30);

        } else if (Game.state == State.GameOver) {

            graphics.setFont(fntO);
            graphics.setColor(Color.BLACK);
            graphics2D.fill(Quit());
            graphics2D.fill(Play());
            if (Player.Money >= 10) {
                graphics2D.fill(Save());
            }

            graphics.setColor(Color.WHITE);
            graphics.drawString("GameOver", Game.HEIGHT / 2, 100);
            graphics.setFont(fnt1);
            if (Player.Money >= 10) {
                graphics.drawString("Respawn (10 Gold)", Save().x + 19, Save().y + 30);
            }
            graphics.drawString("Try Again", Play().x + 19, Play().y + 30);
            graphics.drawString("Quit", Quit().x + 19, Quit().y + 30);

        } else if (Game.state == State.Win) {

            graphics.setFont(fntO);
            graphics.setColor(Color.BLACK);
            graphics2D.fill(Play());
            graphics2D.fill(Quit());


            graphics.setColor(Color.WHITE);
            graphics.drawString("You Won!!", Game.HEIGHT / 2, 100);
            graphics.setFont(fnt1);

            graphics.drawString("Try Again", Play().x + 19, Play().y + 30);
            graphics.drawString("Quit", Quit().x + 19, Quit().y + 30);

        } else if (Game.state == State.Shop) {
            graphics.setFont(fntO);
            graphics.setColor(Color.BLACK);
            graphics2D.fill(Shop());
            graphics2D.fill(Weapon(Game.WIDTH / 2 + 75, 150));
            graphics2D.fill(Weapon(Game.WIDTH / 2 + 75, 250));


            graphics.setColor(Color.WHITE);
            graphics2D.fill(Weapon(Game.WIDTH / 2 - 225, 150));
            graphics2D.fill(Weapon(Game.WIDTH / 2 - 225, 250));
            graphics.drawString("Shop", Game.HEIGHT / 2 - fntO.getSize() * 2, 100);
            graphics.setFont(fnt1);
            graphics.drawString("Back", Shop().x + 19, Shop().y + 30);
            graphics.drawString("Heath + 1 (5)", Weapon(Game.WIDTH / 2 + 75, 150).x + 19, Weapon(Game.WIDTH / 2 + 75, 150).y + 30);
            graphics.drawString("Speed + 1 (10)", Weapon(Game.WIDTH / 2 + 75, 250).x + 19, Weapon(Game.WIDTH / 2 + 75, 250).y + 30);


            BufferedImage weapon_img_axe = loader.loadImage("image/Axe.png");
            BufferedImage weapon_img_spear = loader.loadImage("image/Chapadlo.png");
            graphics.setColor(Color.BLACK);
            graphics.drawImage(weapon_img_axe, Weapon(Game.WIDTH / 2 - 225, 150).x - 60, Weapon(Game.WIDTH / 2 - 225, 150).y - 30, 80, 80, null);
            graphics.drawString("Weapon1 (5)", Weapon(Game.WIDTH / 2 - 225, 150).x + 19, Weapon(Game.WIDTH / 2 - 225, 150).y + 30);
            graphics.drawImage(weapon_img_spear, Weapon(Game.WIDTH / 2 - 225, 250).x - 60, Weapon(Game.WIDTH / 2 - 225, 250).y - 30, 80, 80, null);
            graphics.drawString("Weapon2 (10)", Weapon(Game.WIDTH / 2 - 225, 250).x + 19, Weapon(Game.WIDTH / 2 - 225, 250).y + 30);


        }

    }

    /**
     * This function is used for creating rectangle in the Menu.
     *
     * @return new rectangle
     */
    public Rectangle Play() {
        return new Rectangle(Game.WIDTH / 2 - 225, 150, 180, 50);
    }

    /**
     * This function is used for creating rectangle in the Menu.
     *
     * @return new rectangle
     */
    public Rectangle Settings() {
        return new Rectangle(Game.WIDTH / 2 - 225, 250, 180, 50);
    }

    /**
     * This function is used for creating rectangle in the Menu.
     *
     * @return new rectangle
     */
    public Rectangle Quit() {
        return new Rectangle(Game.WIDTH / 2 - 225, 350, 180, 50);
    }

    /**
     * This function is used for creating rectangle in the Menu.
     *
     * @return new rectangle
     */
    public Rectangle Save() {
        return new Rectangle(Game.WIDTH / 2 + 75, 150, 180, 50);
    }

    /**
     * This function is used for creating rectangle in the Menu.
     *
     * @return new rectangle
     */
    public Rectangle Load() {
        return new Rectangle(Game.WIDTH / 2 + 75, 250, 180, 50);
    }

    /**
     * This function is used for creating rectangle in the Menu.
     *
     * @return new rectangle
     */
    public Rectangle NWGame() {
        return new Rectangle(Game.WIDTH / 2 + 75, 350, 180, 50);
    }

    /**
     * This function is used for creating rectangle in the Menu.
     *
     * @return new rectangle
     */
    public Rectangle Shop() {
        return new Rectangle(Game.WIDTH / 2 - 75, Game.HEIGHT - 100, 180, 50);
    }

    /**
     * This function is used for creating rectangle in the Menu.
     *
     * @return new rectangle
     */
    public Rectangle Weapon(int x, int y) {
        return new Rectangle(x, y, 200, 50);
    }

}
