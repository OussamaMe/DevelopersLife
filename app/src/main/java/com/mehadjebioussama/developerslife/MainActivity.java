package com.mehadjebioussama.developerslife;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
//        viewPager.setPageMargin(20);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//            @Override
//            public void onPageSelected(int i) {
//            }
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });

    }
}