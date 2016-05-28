package com.image;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by stephenokennedy on 27/05/2016.
 */

public class ImageProcessorTest {

    @Test
    public void resizeImage(){
        ImageProcessor processor = new ImageProcessor();
        try {
            BufferedImage img1 = ImageIO.read(new File("./test-images/img1.jpg"));
            BufferedImage img2 = ImageIO.read(new File("./test-images/img2.jpg"));
            BufferedImage img3 = ImageIO.read(new File("./test-images/img3.jpg"));
    //        processor.getAspectRatio(100)
            Dimension img1Small = new Dimension(100,66);
            Dimension img1Medium = new Dimension(220,146);
            Dimension img1Large = new Dimension(320,212);

            Dimension img2Small = new Dimension(100,56);
            Dimension img2Medium = new Dimension(220,123);
            Dimension img2Large = new Dimension(320,179);

            Dimension img3Small = new Dimension(100,40);
            Dimension img3Medium = new Dimension(220,87);
            Dimension img3Large = new Dimension(320,127);
            assertEquals(img1Small.height,processor.resizeImage(img1,ImageSize.SMALL.getValue()).getHeight());
            assertEquals(img1Medium.height,processor.resizeImage(img1,ImageSize.MEDIUM.getValue()).getHeight());
            assertEquals(img1Large.height,processor.resizeImage(img1,ImageSize.LARGE.getValue()).getHeight());

            assertEquals(img2Small.height,processor.resizeImage(img2,ImageSize.SMALL.getValue()).getHeight());
            assertEquals(img2Medium.height,processor.resizeImage(img2,ImageSize.MEDIUM.getValue()).getHeight());
            assertEquals(img2Large.height,processor.resizeImage(img2,ImageSize.LARGE.getValue()).getHeight());

            assertEquals(img3Small.height,processor.resizeImage(img3,ImageSize.SMALL.getValue()).getHeight());
            assertEquals(img3Medium.height,processor.resizeImage(img3,ImageSize.MEDIUM.getValue()).getHeight());
            assertEquals(img3Large.height,processor.resizeImage(img3,ImageSize.LARGE.getValue()).getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
