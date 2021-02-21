package com.mehadjebioussama.developerslife.util;

import javax.inject.Inject;


public class GifFactory {

    private static final int LATEST = 0;
    private static final int TOP = 1;

    private GifType latestType;
    private GifType hotType;
    private GifType topType;

    @Inject
    public GifFactory() {
    }

    public GifType getGifType(int currentItem) {
        if (currentItem == LATEST) {
            if (latestType == null) {
                latestType = new LatestType();
            }
            return latestType;
        } else if (currentItem == TOP) {
            if (topType == null) {
                topType = new TopType();
            }
            return topType;
        }else {
            if(hotType == null){
                hotType = new HotType();
            }
            return hotType;
        }
    }
}
