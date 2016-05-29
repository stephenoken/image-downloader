package com.paser;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
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
        ImageDownloader.downloadsImages(ImageDownloader.getImgUrlsFromSite(this.samplePage),"./test-dir/crosscountryrunning");
        ImageDownloader.downloadsImages(ImageDownloader.getImgUrlsFromSite(this.samplePage),"./test-dir/crosscountryrunning2/");
//        ImageDownloader.downloadsImages(ImageDownloader.getImgUrlsFromSite(this.samplePage),null);
        assertTrue(f1.exists());
        assertTrue(f2.exists());
//        assertTrue(f3.exists());
    }

    /*
    To test whether the program doesn't download the same image twice comment out this
    after test code block and check the last created/modified in finder or windows explorer
     */
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

    @Test
    public void areImagesTheSame(){
        try{
            String file1 = "./test-images/img1.jpg";
            String file2 = "./test-images/img1.jpg";
            String file3 = "./test-images/img2.jpg";
            String file4 = "./test-images/img-doesnt-exist.png";
            assertTrue(ImageDownloader.compare(file1,new FileInputStream(file2)));
            assertFalse(ImageDownloader.compare(file1,new FileInputStream(file3)));
            assertFalse(ImageDownloader.compare(file4,new FileInputStream(file3)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}