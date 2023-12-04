package cz.cvut.fel.pjv.framework;

import cz.cvut.fel.pjv.Game;
import cz.cvut.fel.pjv.window.State;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This Class is for inputs in a menu
 */
public class InputsMenu extends KeyAdapter {


    /**
     * This function is used for a better working menu
     *
     * @param e the event to be processed
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();


        if (key == KeyEvent.VK_ESCAPE) {
            if (Game.state == State.Game) {
                Game.state = State.Menu;
            } else if (Game.state == State.Menu) {
                Game.state = State.Game;
            } else if (Game.state == State.Shop) {
                Game.state = State.Game;
            }
        }
        if (Game.state == State.Menu || Game.state == State.GameOver || Game.state == State.Win) {
            if (key == KeyEvent.VK_R) {
                Game.state = State.Reset;
            }
        }
    }
}
