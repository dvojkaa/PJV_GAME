package cz.cvut.fel.pjv.window;

import cz.cvut.fel.pjv.Game;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for creating a window in which we can play the game.
 */
public class Window {

    /**
     * This function is used for creating Window in which we can play the game.
     *
     * @param height is the height of the Window.
     * @param weight is the weight of the Window.
     * @param title  is the title of the Window.
     * @param game   is the Game itself.
     */
    public Window(int height, int weight, String title, Game game) {
        game.setPreferredSize(new Dimension(weight, height));
        game.setMaximumSize(new Dimension(weight, height));
        game.setMinimumSize(new Dimension(weight, height));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }
}
