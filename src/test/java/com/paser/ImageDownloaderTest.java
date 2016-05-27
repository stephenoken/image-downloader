package com.paser;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by stephenokennedy on 27/05/2016.
 */
public class ImageDownloaderTest {

    private String samplePage = "http://crosscountryrunning.tumblr.com/";
    private String imgUrl = "http://66.media.tumblr.com/avatar_284cfbb01f43_128.png";
    @Test
    public void getImgUrlsFromSite(){
        ImageDownloader downloader = new ImageDownloader();
        assertTrue(downloader.getImgUrlsFromSite(this.samplePage).contains(this.imgUrl));
        assertTrue(downloader.getImgUrlsFromSite(null).isEmpty());
    }

    @Test
    public void downloadImages(){
        File f = new File("./test/crosscountryrunning/avatar_284cfbb01f43_128.png");
        ImageDownloader downloader  = new ImageDownloader();
        downloader.downloadsImages(downloader.getImgUrlsFromSite(this.samplePage),"test");
        assertTrue(f.exists());
    }

    @Test
    public void parseFileName(){
        assertEquals("/avatar_284cfbb01f43_128.png",
                new ImageDownloader().getFileName(this.imgUrl));
    }

    @Test
    public void createDirectory(){
        String path1 = "./sample-dir/images";
        File f1 = new File(path1);
        assertTrue(f1.isDirectory());
    }
}