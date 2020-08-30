package com.mehadjebioussama.developerslife.util.factory;

import static com.mehadjebioussama.developerslife.util.Constansts.LATEST;
import static com.mehadjebioussama.developerslife.util.Constansts.TOP;

public class GifFactory {
    private GifType latestType;
    private GifType hotType;
    private GifType topType;
    public GifType getGifType(int currentItem){
        if(currentItem == LATEST){
            if(latestType == null){
                latestType = new LatestType();
            }
            return latestType;
        }else if(currentItem == TOP){
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
