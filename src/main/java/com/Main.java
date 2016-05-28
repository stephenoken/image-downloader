package com;

import com.paser.ImageDownloader;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by stephenokennedy on 27/05/2016.
 */
public class Main {

    public static void main(String[] args){
        if (args.length != 2){
            System.err.println("Missing Arguments Url and directory");
        }
        ImageDownloader downloader = new ImageDownloader();
        downloader.downloadsImages(downloader.getImgUrlsFromSite(args[0]),args[1]);
    }
}
