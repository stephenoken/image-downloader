package com.paser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by stephenokennedy on 27/05/2016.
 */

public class ImageDownloader {


    public List<String> getImgUrlsFromSite(String website) {
        ArrayList<String> imgUrls = new ArrayList();
        try{
            Document doc = Jsoup.connect(website).get();
            Elements imageTags = doc.getElementsByTag("img");
            for (Element e:imageTags) {
                imgUrls.add(e.attr("src"));
            }
        }catch (Exception e){
            System.err.println("Site Didn't have any images");
        }
        return  imgUrls;
    }
}
