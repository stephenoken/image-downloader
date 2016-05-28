package com.paser;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
        File f1 = new File("./test-dir/crosscountryrunning/avatar_284cfbb01f43_128.png");
        File f2 = new File("./test-dir/crosscountryrunning2/avatar_284cfbb01f43_128.png");
//        File f3 = new File("./avatar_284cfbb01f43_128.png");
        ImageDownloader downloader  = new ImageDownloader();
        downloader.downloadsImages(downloader.getImgUrlsFromSite(this.samplePage),"./test-dir/crosscountryrunning");
        downloader.downloadsImages(downloader.getImgUrlsFromSite(this.samplePage),"./test-dir/crosscountryrunning2/");
//        downloader.downloadsImages(downloader.getImgUrlsFromSite(this.samplePage),null);
        assertTrue(f1.exists());
        assertTrue(f2.exists());
//        assertTrue(f3.exists());
    }

    @After
    public void removeTestDir(){
        try{
            FileUtils.deleteDirectory(new File("./test-dir"));
        }catch (IOException e){
            System.out.println("Folder not found");
        }
    }
    @Test
    public void parseFileName(){
        assertEquals("avatar_284cfbb01f43_128.png",
                new ImageDownloader().getFileName(this.imgUrl));
    }
}