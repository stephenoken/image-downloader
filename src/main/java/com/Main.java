package com;

import com.image.ImageProcessor;
import com.paser.ImageDownloader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static com.paser.ImageDownloader.*;

/**
 * Created by stephenokennedy on 27/05/2016.
 */
public class Main {

    public static void main(String[] args){
        if (args.length != 2){
            System.err.println("Missing Arguments Url and directory");
        }
        ImageDownloader.downloadsImages(ImageDownloader.getImgUrlsFromSite(args[0]),args[1]);
        getFilesFromDirectory(args[1]);
    }

    public static void getFilesFromDirectory(String directory){
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        try {
            for (File f:listOfFiles) {
                if (f.exists()&& f.isFile());
                ImageProcessor.generateScaledImages(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
