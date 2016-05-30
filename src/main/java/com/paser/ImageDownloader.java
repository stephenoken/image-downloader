package com.paser;

import com.image.ImageProcessor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
            Document doc = Jsoup.connect(website).userAgent("Mozilla").get();
            Elements imageTags = doc.getElementsByTag("img");
            for (Element e:imageTags) {
                String url = validateURL(website,e.attr("src"));
                if (isSupportedFormat(url))
                imgUrls.add(url);
            }
        }catch (Exception e){
            System.err.println(e.toString());
        }
        return  imgUrls;
    }

    private static boolean isSupportedFormat(String url) {
        return url.endsWith(".jpg") || url.endsWith(".JPEG") || url.endsWith(".png") || url.endsWith(".gif");
    }

    public static void downloadsImages(List<String> imgUrlsFromSite, String destination){
        String dest = (destination != null)?
                (destination.endsWith("/"))? destination : destination + "/" : "./";

        ExecutorService executorService = Executors.newCachedThreadPool();
        new File(dest).mkdirs();
        for (String link:imgUrlsFromSite) {
            executorService.submit(() -> {
                try {
                    String fileDir = dest + getFileName(link);
                    final URL url = new URL(link);
                    URLConnection connection = url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");
                    InputStream is = connection.getInputStream();
                    if (!ImageDownloader.doChecksumsMatch(fileDir, is)) {
                        File f = new File(fileDir);
                        String fileType = f.getName().substring(f.getName().lastIndexOf(".")+1);

                        ImageIO.write(ImageIO.read(url),fileType,f);
//                        OutputStream os = new FileOutputStream(fileDir,false);
//                        byte[] b = new byte[2048];
//                        int length;
//                        while ((length = is.read(b)) != -1) {
//                            os.write(b, 0, length);
//                        }
//                        os.flush();
//                        os.close();
                        ImageProcessor.generateScaledImages(new File(fileDir));

                    }

                    is.close();
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
            });
        }
        executorService.shutdown();
    }

    public static String getFileName(String imgUrl){
        return imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
    }

    public static boolean doChecksumsMatch(String file, InputStream stream2) throws IOException, NoSuchAlgorithmException{
        if (!new File(file).exists())
            return false;
        MessageDigest md1 = MessageDigest.getInstance("MD5");
        MessageDigest md2 = MessageDigest.getInstance("MD5");
        InputStream originalStream = new FileInputStream(file);

        InputStream hashedStream1 = new DigestInputStream(originalStream,md1);
        InputStream hashedStream2 = new DigestInputStream(stream2,md2);

        Boolean result = IOUtils.contentEquals(hashedStream1,hashedStream2);

        originalStream.close();
        hashedStream1.close();
        return result;

    }

    public static String validateURL(String webUrl, String imgUrl) {
        String formatWebUrl = (webUrl.endsWith("/"))? webUrl : webUrl + "/";

        try{
            return new URL(imgUrl).toString();
        }catch (MalformedURLException e){
            return formatWebUrl +
                    ((imgUrl.startsWith("/"))? imgUrl.substring(1,imgUrl.length()):imgUrl);
        }
    }
}
