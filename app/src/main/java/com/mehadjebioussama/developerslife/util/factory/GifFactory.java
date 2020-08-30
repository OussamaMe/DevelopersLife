package com.mehadjebioussama.developerslife.util.factory;

public class GifFactory {
    private GifType latestType;
    private GifType hotType;
    private GifType topType;
    public GifType getGifType(int currentItem){
        if(currentItem == 0){
            if(latestType == null){
                latestType = new LatestType();
            }
            return latestType;
        }else if(currentItem == 1){
            if(topType == null){
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
