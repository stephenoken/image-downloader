package com.paser;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static List<String> getImgUrlsFromSite(String website) {
        ArrayList<String> imgUrls = new ArrayList<>();
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

    private static boolean isSupportedFormat(String url) {
        return url.endsWith(".jpg") || url.endsWith(".JPEG") || url.endsWith(".png") || url.endsWith(".gif");
    }

    public static void downloadsImages(List<String> imgUrlsFromSite, String destination){

        String dest = (destination != null)?
                (destination.endsWith("/"))? destination : destination + "/" : "./";
        try{
            new File(dest).mkdirs();
            for (String link:imgUrlsFromSite) {
                String fileDir = dest+getFileName(link);
                final URL url = new URL(link);
                InputStream is = url.openStream();
                if (!ImageDownloader.compare(fileDir,is)) {
                    OutputStream os = new FileOutputStream(fileDir);

                    byte[] b = new byte[2048];
                    int length;
                    while ((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }
                    os.close();
                }

                is.close();
            }
        } catch(Exception e){
            System.err.println(e);
        }

    }

    public static String getFileName(String imgUrl){
        return imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
    }

    public static boolean compare(String file, InputStream stream2) throws IOException, NoSuchAlgorithmException{
        if (!new File(file).exists())
            return false;
        MessageDigest md1 = MessageDigest.getInstance("MD5");
        MessageDigest md2 = MessageDigest.getInstance("MD5");
        InputStream originalStream = new FileInputStream(file);

        InputStream hashedStream1 = new DigestInputStream(originalStream,md1);
        InputStream hashedStream2 = new DigestInputStream(stream2,md2);

        return IOUtils.contentEquals(hashedStream1,hashedStream2);
    }
}
