package com.paser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by stephenokennedy on 27/05/2016.
 */

public class ImageDownloader {

    /*
        Scrapes Website for images for pngs, gifs and jpegs
        @returns : A list of image urls
    */
    public List<String> getImgUrlsFromSite(String website) {
        ArrayList<String> imgUrls = new ArrayList();
        try{
            Document doc = Jsoup.connect(website).get();
            Elements imageTags = doc.getElementsByTag("img");
            for (Element e:imageTags) {
                String url = e.attr("src");
                if (isSupportedFormat(url))
                imgUrls.add(e.attr("src"));
            }
        }catch (Exception e){
            System.err.println("Site Didn't have any images");
        }
        return  imgUrls;
    }

    private boolean isSupportedFormat(String url) {
        return url.endsWith(".jpg") || url.endsWith(".JPEG") || url.endsWith(".png") || url.endsWith(".gif");
    }

    public void downloadsImages(List<String> imgUrlsFromSite, String destination){

        String dest = (destination != null)?
                (destination.endsWith("/"))? destination : destination + "/" : "./";
        try{
            new File(dest).mkdirs();
            for (String link:imgUrlsFromSite) {
                final URL url = new URL(link);
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(dest+this.getFileName(link));

                byte[] b = new byte[2048];
                int length;
                while((length = is.read(b))!= -1){
                    os.write(b,0,length);
                }

                is.close();
                os.close();
            }
        } catch(IOException e){
            System.err.println(e);
        }

    }

    public String getFileName(String imgUrl){
        return imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
    }

}
