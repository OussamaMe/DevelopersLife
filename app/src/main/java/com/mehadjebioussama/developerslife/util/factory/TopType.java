package com.mehadjebioussama.developerslife.util.factory;

public class TopType implements GifType {
    int currentGif = 0;
    @Override
    public String getType() {
        return "top";
    }

    @Override
    public void incrementCurrentGif() {
        currentGif++;
    }

    @Override
    public void decrementCurrentGif() {
        currentGif--;
    }

    @Override
    public int getCurrentGif() {
        return currentGif;
    }
}
