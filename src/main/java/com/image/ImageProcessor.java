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
        double scaleY = (image.getHeight()/ (double) image.getWidth())*width;
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
        String smallDirPath = file.getParent()+"/../small/";
        String mediumDirPath = file.getParent()+"/../medium/";
        String largeDirPath = file.getParent()+"/../large/";

        new File(smallDirPath).mkdirs();
        new File(mediumDirPath).mkdirs();
        new File(largeDirPath).mkdirs();
        BufferedImage image = ImageIO.read(file);

        BufferedImage sImg = resizeImage(image,ImageSize.SMALL.getValue());
        BufferedImage mImg = resizeImage(image,ImageSize.MEDIUM.getValue());
        BufferedImage lImg = resizeImage(image,ImageSize.LARGE.getValue());

        new Thread(()->{generateImages(file, smallDirPath, sImg); }).start();
        new Thread(()->{generateImages(file, mediumDirPath, mImg);}).start();
        new Thread(()->{generateImages(file, largeDirPath, lImg);}).start();


    }

    private static void generateImages(File file, String directoryPath, BufferedImage image) {
        String fileName = file.getName().substring(0,file.getName().indexOf(".")+1);
        //Ensures that we use RGB instead of RGBA on the  png to jpeg conversion
        try{
            ImageIO.write(getJPGFriendlyImage(file, image), ImageTypes.JPG.toString(),
                    new File(directoryPath+fileName+ImageTypes.JPG));
            ImageIO.write(image,ImageTypes.PNG.toString(),
                    new File(directoryPath+fileName+ImageTypes.PNG));
            ImageIO.write(image,ImageTypes.GIF.toString(),
                    new File(directoryPath+fileName+ImageTypes.GIF));
        } catch (IOException e){
            System.err.println(e.toString());
        }
    }

    private static BufferedImage getJPGFriendlyImage(File file, BufferedImage image) {
        if (fileIsPNG(file)) {

            BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            jpgImage.createGraphics().drawImage(image,0,0, Color.white,null);
            return  jpgImage;
        }else{
            return image;
        }
    }

    private static boolean fileIsPNG(File file) {
        return file.getName().toLowerCase().endsWith(".png");
    }

}
