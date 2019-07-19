package com.example.josephnyangaresi.shake;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

       private Context context;
       private LayoutInflater layoutInflater;
        public   SliderAdapter(Context context){
            this.context=context;
        }
        //Arrays
    private int[] slide_images={

               R.drawable.profileicon,
                R.drawable.recipeicon,
                R.drawable.muffinicon,
        };
        private String[] slide_headings={
                "CREATE PROFILE",
                "CREATE RECIPE",
                "VIEW RECIPE'S",
        };
        private String[] slide_desc={
                "create a user profile that will be an identifier when you log into the application",
                "Create your own recipes and post them in the app",
                "View recipes created by other users and enhance your cooing experience and kowledge"

        };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view ==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView =  view.findViewById(R.id.slideimage);
        TextView slideHeading =  view.findViewById(R.id.slideheader);
        TextView slideDescription = view.findViewById(R.id.slidedescription);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);
        container.addView(view);
            return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
      //  super.destroyItem(container,position,object);
        container.removeView((RelativeLayout)object);
    }
}
