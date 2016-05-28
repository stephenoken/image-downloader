package com.utils;

/**
 * Created by stephenokennedy on 28/05/2016.
 */
public class ImageUtils {
    public String getFileName(String imgUrl){
        return imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
    }
}
