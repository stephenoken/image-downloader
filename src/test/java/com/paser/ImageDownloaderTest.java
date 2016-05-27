package com.paser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by stephenokennedy on 27/05/2016.
 */
public class ImageDownloaderTest {

    @Test
    public void getImgUrlsFromSite(){
        String samplePage = "http://crosscountryrunning.tumblr.com/";
        String imgUrl = "http://66.media.tumblr.com/avatar_284cfbb01f43_128.png";
        ImageDownloader downloader = new ImageDownloader();
        assertEquals(true,downloader.getImgUrlsFromSite(samplePage).contains(imgUrl));
        assertEquals(true, downloader.getImgUrlsFromSite(null).isEmpty());
    }
}