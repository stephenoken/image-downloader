package com.image;

/**
 * Created by stephenokennedy on 28/05/2016.
 */
public enum ImageTypes {
    JPG, PNG, GIF;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
