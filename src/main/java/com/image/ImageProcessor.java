package com.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by stephenokennedy on 27/05/2016.
 * Performs the image manipulation and validaiton
 */
public class ImageProcessor {

    public BufferedImage resizeImage(BufferedImage image,int width) {
        double scaleX = (double) width;
        double scaleY = (image.getHeight()/(double)image.getWidth())*width;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX,scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform,AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(image,new BufferedImage(width,(int)Math.round(scaleY),image.getType()));
    }
}
