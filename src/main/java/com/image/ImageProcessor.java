package com.image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by stephenokennedy on 27/05/2016.
 * Performs the image manipulation and validaiton
 */
public class ImageProcessor{

    public static BufferedImage resizeImage(BufferedImage image,int width) {

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

    public static void generateScaledImages(File file) throws IOException{
        String smallDirPath = file.getParent()+"/small/";
        String mediumDirPath = file.getParent()+"/medium/";
        String largeDirPath = file.getParent()+"/large/";

        new File(smallDirPath).mkdirs();
        new File(mediumDirPath).mkdirs();
        new File(largeDirPath).mkdirs();

        BufferedImage sImg = resizeImage(ImageIO.read(file),ImageSize.SMALL.getValue());
        BufferedImage mImg = resizeImage(ImageIO.read(file),ImageSize.MEDIUM.getValue());
        BufferedImage lImg = resizeImage(ImageIO.read(file),ImageSize.LARGE.getValue());

        // TODO: 28/05/2016 Implement threading
        generateImages(file, smallDirPath, sImg);
        generateImages(file, mediumDirPath, mImg);
        generateImages(file, largeDirPath, lImg);


    }

    private static void generateImages(File file, String smallDirPath, BufferedImage sImg) throws IOException {
        String fileName = file.getName().substring(0,file.getName().indexOf(".")+1);

        ImageIO.write(sImg, ImageTypes.JPG.toString(),
                new File(smallDirPath+fileName+ImageTypes.JPG));
        ImageIO.write(sImg,ImageTypes.PNG.toString(),
                new File(smallDirPath+fileName+ImageTypes.PNG));
        ImageIO.write(sImg,ImageTypes.GIF.toString(),
                new File(smallDirPath+fileName+ImageTypes.GIF));
    }
}
