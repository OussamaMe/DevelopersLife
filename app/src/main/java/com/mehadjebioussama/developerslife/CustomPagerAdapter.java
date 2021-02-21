package com.mehadjebioussama.developerslife;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class CustomPagerAdapter extends PagerAdapter {
    private static final int LATEST = 0;
    private static final int TOP = 1;
    private static final int HOT = 2;
    private static final int NUM_OF_CATEGORIES = 3;

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
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
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
        switch (position) {
            case LATEST:
                return context.getString(R.string.latest);
            case TOP:
                return context.getString(R.string.top);
            case HOT:
                return context.getString(R.string.hot);
        }
        return "Error";
    }
}
