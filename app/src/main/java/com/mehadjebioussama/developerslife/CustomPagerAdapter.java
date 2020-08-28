package com.mehadjebioussama.developerslife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;

public class CustomPagerAdapter extends PagerAdapter {

    Context context;

    public CustomPagerAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public  Object instantiateItem(ViewGroup container, int position) {
//        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, container, false);
//        TextView imageView = view.findViewById(R.id.image);
//        imageView.setBackgroundResource(pager.get(position));
//        container.addView(view);
//
//        return view;
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Последние";
        } else if (position == 1) {
            return "Лучшие";
        } else return "Горячие";
    }
}
