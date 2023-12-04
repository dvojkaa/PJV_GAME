package cz.cvut.fel.pjv.framework.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used for finding and loading images.
 */
public class IMG {

    /**
     * This function is used for loading images for the game.
     *
     * @param path is the path in computer for the image.
     * @return the image
     */

    public BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            File imageFile = new File("data/" + path);
            if (imageFile.exists()) {
                image = ImageIO.read(imageFile);
            } else {
                throw new IOException("Image file not found: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


}

