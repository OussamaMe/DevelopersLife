package com.mehadjebioussama.developerslife.util.factory;

public class HotType implements GifType {
    int currentGif = 0;
    @Override
    public String getType() {
        return "hot";
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
