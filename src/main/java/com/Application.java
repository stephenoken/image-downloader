package com;


import com.google.common.base.Stopwatch;
import com.download.ImageDownloader;

import java.util.concurrent.TimeUnit;

/**
 * Created by stephenokennedy on 27/05/2016.
 */
public class Application {

    public static void main(String[] args){
        if (args.length != 2){
            System.err.println("Missing Arguments Url and directory");
        }
        final Stopwatch stopwatch = Stopwatch.createStarted();
        ImageDownloader.downloadsImages(ImageDownloader.getImgUrlsFromSite(args[0]),args[1]);
        final long elapsedMillis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("CPU time " + elapsedMillis +" milliseconds.");
    }
}
