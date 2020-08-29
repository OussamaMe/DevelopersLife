package com.mehadjebioussama.developerslife.mainactivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mehadjebioussama.developerslife.CustomPagerAdapter;
import com.mehadjebioussama.developerslife.R;
import com.mehadjebioussama.developerslife.db.GifDbModel;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MainActivity extends MvpAppCompatActivity implements MainContract.View {
    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    MainPresenter provideRepositoryPresenter() {
        MainRepository repository = new MainRepository();
        return new MainPresenter(repository);
    }

    private ViewPager viewPager;
    private ProgressBar progressBar;
    private ImageView gif;
    private TextView description;
    private CardView next;
    private CardView previous;
    private RelativeLayout errorLayout;
    private CardView cardView;
    private RelativeLayout buttonsLayout;
    private TextView repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initViews();

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        next.setOnClickListener(view -> {
            presenter.onNextClick();
            previous.setEnabled(true);
        });

        previous.setEnabled(false);
        previous.setOnClickListener(view -> {
            presenter.onPreviousClick();
        });

        repeat.setOnClickListener(view -> presenter.tryAgain());

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

    private void initViews() {
        viewPager = findViewById(R.id.viewpager);
        progressBar = findViewById(R.id.loading_gif);
        gif = findViewById(R.id.gif);
        description = findViewById(R.id.gif_description);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        errorLayout = findViewById(R.id.error_message);
        cardView = findViewById(R.id.card_view);
        buttonsLayout = findViewById(R.id.buttons);
        repeat = findViewById(R.id.repeat);
    }

    @Override
    public void loadingGif() {
        progressBar.setVisibility(View.VISIBLE);
        gif.setVisibility(View.INVISIBLE);
        description.setVisibility(View.INVISIBLE);
        errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void showGif(GifDbModel gifDbModel) {
        description.setVisibility(View.VISIBLE);
        description.setText(gifDbModel.getDescription());
        gif.setVisibility(View.VISIBLE);
        Glide
                .with(this)
                .load(gifDbModel.getImageUrl())
                .centerCrop()
                .into(gif);
        errorLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(Throwable throwable) {
        cardView.setVisibility(View.INVISIBLE);
        buttonsLayout.setVisibility(View.INVISIBLE);
        errorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void disablePreviousButton() {
        previous.setEnabled(false);
    }

    @Override
    public void hideError() {
        errorLayout.setVisibility(View.GONE);
        cardView.setVisibility(View.VISIBLE);
        buttonsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onActivityDestroy();
    }
}