package com.mehadjebioussama.developerslife.mainactivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.mehadjebioussama.developerslife.CustomPagerAdapter;
import com.mehadjebioussama.developerslife.GifApp;
import com.mehadjebioussama.developerslife.databinding.ActivityMainBinding;
import com.mehadjebioussama.developerslife.db.GifDbModel;

import javax.inject.Inject;

import androidx.viewpager.widget.ViewPager;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MainActivity extends MvpAppCompatActivity implements MainContract.View, ViewPager.OnPageChangeListener {

    @InjectPresenter
    MainPresenter presenter;

    @Inject
    MainPresenter presenterProvider;

    @ProvidePresenter
    MainPresenter provideRepositoryPresenter() {
        return presenterProvider;
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((GifApp) getApplication()).getAppComponent().injectMainActivity(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        setContentView(binding.getRoot());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(this);
        binding.viewpager.setAdapter(pagerAdapter);

        binding.next.setOnClickListener(view -> presenter.onNextClick(binding.viewpager.getCurrentItem()));

        binding.previous.setOnClickListener(view -> presenter.onPreviousClick(binding.viewpager.getCurrentItem()));

        binding.repeat.setOnClickListener(view -> presenter.tryAgain(binding.viewpager.getCurrentItem()));
        binding.viewpager.addOnPageChangeListener(this);
    }

    @Override
    public void loadingGif() {
        binding.loadingGif.setVisibility(View.VISIBLE);
        binding.gif.setVisibility(View.INVISIBLE);
        binding.gifDescription.setVisibility(View.INVISIBLE);
        binding.errorMessage.setVisibility(View.GONE);
    }

    @Override
    public void showGif(GifDbModel gifDbModel) {
        binding.gifDescription.setVisibility(View.VISIBLE);
        binding.gifDescription.setText(gifDbModel.getDescription());
        binding.gif.setVisibility(View.VISIBLE);
        Glide
                .with(this)
                .load(gifDbModel.getImageUrl())
                .centerCrop()
                .into(binding.gif);
        binding.errorMessage.setVisibility(View.GONE);
        binding.loadingGif.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(Throwable throwable) {
        binding.cardView.setVisibility(View.INVISIBLE);
        binding.buttons.setVisibility(View.INVISIBLE);
        binding.errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void disablePreviousButton() {
        binding.previous.setEnabled(false);
    }

    @Override
    public void hideError() {
        binding.errorMessage.setVisibility(View.GONE);
        binding.cardView.setVisibility(View.VISIBLE);
        binding.buttons.setVisibility(View.VISIBLE);
    }

    @Override
    public void enablePreviousButton() {
        binding.previous.setEnabled(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageSelected(int position) {
        presenter.tryAgain(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) { }

}