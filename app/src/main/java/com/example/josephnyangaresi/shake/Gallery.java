package com.example.josephnyangaresi.shake;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Gallery extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ViewPager viewPager=findViewById(R.id.ViewPager);
        ImageAdapter adapter=new ImageAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
