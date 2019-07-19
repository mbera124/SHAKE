package com.example.josephnyangaresi.shake;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private DrawerLayout dl;
        private ActionBarDrawerToggle t;
        private NavigationView nv;
        private Button btnvideos,btngallery,btnrecipe;
        private ImageView imageView;

        @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            dl =  findViewById(R.id.activity_main);
            t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
            btngallery=findViewById(R.id.btngalley);
            btnrecipe=findViewById(R.id.btnrecipe);
            btnvideos=findViewById(R.id.btnvideos);
            imageView=findViewById(R.id.imageView);
            dl.addDrawerListener(t);
            t.syncState();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            nv =findViewById(R.id.nv);

           btngallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Gallery.class));
                    finish();
                }
            });

            btnvideos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Videos.class));
                    finish();
                }
            });

            btnrecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, Recipe.class));
                    finish();
                }
            });

            nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    switch(id)
                    {
                        case R.id.account:
                            startActivity(new Intent(MainActivity.this, Profile.class));
                            finish();
                        case R.id.settings:
                            startActivity(new Intent(MainActivity.this, Settings.class));
                            finish();
                        case R.id.mycart:
                            startActivity(new Intent(MainActivity.this, Cart.class));
                            finish();
                        case R.id.logout:
                            startActivity(new Intent(MainActivity.this, Logout.class));
                            finish();
                        default:
                            return true;
                    }
                        }
            });


        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            if(t.onOptionsItemSelected(item))
                return true;

            return super.onOptionsItemSelected(item);
        }
    }