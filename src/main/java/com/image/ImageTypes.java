package com.image;

/**
 * Created by stephenokennedy on 28/05/2016.
 */
public enum ImageTypes {
    JPG ("jpg"),
    PNG ("png"),
    GIF ("gif");

    private String type;
    ImageTypes(String type){
        this.type = type;
    }
}
