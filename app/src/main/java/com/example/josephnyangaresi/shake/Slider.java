package com.example.josephnyangaresi.shake;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Slider extends AppCompatActivity {
    private ViewPager slideviewpager;
    private LinearLayout dotslayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mdots;
    private int pagePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        slideviewpager = findViewById(R.id.slideviewpager);
        dotslayout = findViewById(R.id.dotslayout);

        sliderAdapter = new SliderAdapter(this);
        slideviewpager.setAdapter(sliderAdapter);
        addDotsIndicator();
        slideviewpager.addOnPageChangeListener(viewListener);

    }

    public void addDotsIndicator() {
        mdots = new TextView[3];
        for (int i = 0; i < mdots.length; i++) {
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextColor(getResources().getColor(R.color.white));
            dotslayout.addView(mdots[i]);
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator();
            pagePosition = i;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                if (pagePosition == mdots.length - 1) {
                    launchHomeScreen();
                }
            }
        }
    };

    private void launchHomeScreen() {
        startActivity(new Intent(Slider.this, login.class));
        finish();
    }
}