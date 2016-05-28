package com.image;

import com.utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by stephenokennedy on 27/05/2016.
 * Performs the image manipulation and validaiton
 */
public class ImageProcessor extends ImageUtils{

    public BufferedImage resizeImage(BufferedImage image,int width) {

//        Calculates the height while maintaining aspect ratio.
        double scaleY = (image.getHeight()/(double) image.getWidth())*width;
        int height = (int)Math.round(scaleY);
        BufferedImage scaledImage =  new BufferedImage(width,height,image.getType());
        Graphics2D g = scaledImage.createGraphics();
        g.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image,0,0,width,height,null);
        g.dispose();
        return scaledImage;
    }

    public void generateScaledImages(File file) throws IOException{
        String smallDirPath = file.getParent()+"/small/";
        String mediumDirPath = file.getParent()+"/medium/";
        String largeDirPath = file.getParent()+"/large/";

        new File(smallDirPath).mkdirs();
        new File(mediumDirPath).mkdirs();
        new File(largeDirPath).mkdirs();

        BufferedImage sImg = resizeImage(ImageIO.read(file),ImageSize.SMALL.getValue());
        String fileName = file.getName().substring(0,file.getName().indexOf("."));
        ImageIO.write(sImg,"jpg",new File(smallDirPath+fileName+".jpg"));
        ImageIO.write(sImg,"png",new File(smallDirPath+fileName+".png"));
        ImageIO.write(sImg,"gif",new File(smallDirPath+fileName+".gif"));


    }
}
