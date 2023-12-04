package cz.cvut.fel.pjv.window;


import cz.cvut.fel.pjv.framework.GameObject;

/**
 * This class is used for creating the following camera.
 */
public class Camera {

    private float x, y;

    /**
     * This function is used for the whole class, so it can be tracked with in the game.
     *
     * @param x is x cord. of the camera.
     * @param y is y cord. of the camera.
     */
    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function is called every tick of the Game.
     *
     * @param Player is GameObject, which the camera follows.
     */
    public void tick(GameObject Player) {
        x = -Player.getX() + 368;
        y = Player.getY() - 268;
    }

    /**
     * This function is used for returning x of the camera.
     *
     * @return the actual x of the camera.
     */
    public float getX() {
        return x;
    }

    /**
     * This function is used for returning y of the camera.
     *
     * @return the actual y of the camera.
     */
    public float getY() {
        return y;
    }
}
