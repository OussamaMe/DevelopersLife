package com.mehadjebioussama.developerslife.mainactivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mehadjebioussama.developerslife.CustomPagerAdapter;
import com.mehadjebioussama.developerslife.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MainActivity extends MvpAppCompatActivity implements MainContract.View{
    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    MainPresenter provideRepositoryPresenter() {
        MainRepository repository = new MainRepository();
        return new MainPresenter(repository);
    }

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        Glide
                .with(this)
                .load("http://static.devli.ru/public/images/gifs/201306/ef1ff2e2-505b-4732-929c-4c9bc3d68abb.gif")
                .centerCrop()
                .into(((ImageView)findViewById(R.id.gif)));
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