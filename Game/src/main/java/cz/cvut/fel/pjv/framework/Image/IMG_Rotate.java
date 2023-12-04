package cz.cvut.fel.pjv.framework.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class is used for rotating images.
 */
public class IMG_Rotate {


    /**
     * This function is used for rotating image if needed.
     * @param img is the image that we want to rotate.
     * @param degree is the degree in which we want to rotate the image.
     * @return the new rotated image
     */
    public static BufferedImage rotate(BufferedImage img, int degree) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, img.getType());
        Graphics2D g2 = newImage.createGraphics();
        g2.rotate(Math.toRadians(degree), (double) width / 2, (double) height / 2);
        g2.drawImage(img, null, 0, 0);
        return newImage;

    }
}
