package com;


import com.paser.ImageDownloader;

/**
 * Created by stephenokennedy on 27/05/2016.
 */
public class Main {

    public static void main(String[] args){
        if (args.length != 2){
            System.err.println("Missing Arguments Url and directory");
        }
        ImageDownloader.downloadsImages(ImageDownloader.getImgUrlsFromSite(args[0]),args[1]);
    }
}
