package com.image;

/**
 * Created by stephenokennedy on 27/05/2016.
 */
public enum ImageSize {
    SMALL (100),
    MEDIUM (220),
    LARGE (320);

    private final int imageSize;

    ImageSize(int size){
        this.imageSize = size;
    }

    public int getValue(){
        return imageSize;
    }



}
