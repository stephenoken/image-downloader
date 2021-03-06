package com.image;

import org.apache.commons.io.FileUtils;
import org.junit.After;
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
            BufferedImage img1 = ImageIO.read(new File("./test-images/originals/img1.jpg"));
            BufferedImage img2 = ImageIO.read(new File("./test-images/originals/img2.jpg"));
            BufferedImage img3 = ImageIO.read(new File("./test-images/originals/img3.jpg"));

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

    @Test
    public void generateScaledImages(){
        try{
            File file1 = new File("./test-images/originals/img1.jpg");
            File file2 = new File("./test-images/originals/img2.jpg");
            File file3 = new File("./test-images/originals/img3.jpg");

            ImageProcessor.generateScaledImages(file1);
            ImageProcessor.generateScaledImages(file2);
            ImageProcessor.generateScaledImages(file3);

            File smallDirectory = new File(file1.getParent()+"/../small/");
            File mediumDirectory = new File(file1.getParent()+"/../medium/");
            File largeDirectory = new File(file1.getParent()+"/../large/");

            assertTrue(smallDirectory.isDirectory());
            assertTrue(mediumDirectory.isDirectory());
            assertTrue(largeDirectory.isDirectory());

            Thread.sleep(1000);

            imageGenerationTestCases();

        } catch(IOException e){
         e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void imageGenerationTestCases() {
        assertTrue(new File("./test-images/small/img1.jpg").exists());
        assertTrue(new File("./test-images/small/img1.png").exists());
        assertTrue(new File("./test-images/small/img1.gif").exists());
        assertTrue(new File("./test-images/small/img2.jpg").exists());
        assertTrue(new File("./test-images/small/img3.png").exists());

        assertTrue(new File("./test-images/medium/img1.jpg").exists());
        assertTrue(new File("./test-images/medium/img1.png").exists());
        assertTrue(new File("./test-images/medium/img1.gif").exists());
        assertTrue(new File("./test-images/medium/img2.gif").exists());
        assertTrue(new File("./test-images/medium/img3.png").exists());

        assertTrue(new File("./test-images/large/img1.jpg").exists());
        assertTrue(new File("./test-images/large/img1.png").exists());
        assertTrue(new File("./test-images/large/img1.gif").exists());
        assertTrue(new File("./test-images/large/img2.gif").exists());
        assertTrue(new File("./test-images/large/img3.png").exists());
    }

    @After
    public void removeTestDirs(){
        try{
            FileUtils.deleteDirectory(new File("./test-images/small"));
            FileUtils.deleteDirectory(new File("./test-images/medium"));
            FileUtils.deleteDirectory(new File("./test-images/large"));
        }catch (IOException e){
            System.out.println("Folder not found");
        }
    }
}
