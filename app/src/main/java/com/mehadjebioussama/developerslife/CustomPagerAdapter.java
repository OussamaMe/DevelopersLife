package com.mehadjebioussama.developerslife;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import static com.mehadjebioussama.developerslife.util.Constansts.LATEST;
import static com.mehadjebioussama.developerslife.util.Constansts.NUM_OF_CATEGORIES;
import static com.mehadjebioussama.developerslife.util.Constansts.TOP;

public class CustomPagerAdapter extends PagerAdapter {

    Context context;

    public CustomPagerAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return NUM_OF_CATEGORIES;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public  Object instantiateItem(@NonNull ViewGroup container, int position) {
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == LATEST) {
            return context.getString(R.string.latest);
        } else if (position == TOP) {
            return context.getString(R.string.top);
        } else return context.getString(R.string.hot);
    }
}
