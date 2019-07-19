package com.example.josephnyangaresi.shake;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static com.example.josephnyangaresi.shake.R.*;

public class Profile extends AppCompatActivity {
    FirebaseAuth Auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TextView tvfirstname, tvlastname, tvemail, tvphonenumber;
    private ImageView imgprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_profile);
        tvemail = findViewById(id.tvemail);
        tvfirstname = findViewById(id.tvfirstname);
        tvlastname = findViewById(id.tvlastName);
        tvphonenumber = findViewById(id.tvphonenumber);
        imgprofile = findViewById(id.imgprofile);

        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String firstname = "" + ds.child("firstname").getValue();
                    String lastname = "" + ds.child("lasttname").getValue();
                    String email = "" + ds.child("email").getValue();
                    String phonenumber = "" + ds.child("phonenumber").getValue();
                    String profileimage = "" + ds.child("profileimage").getValue();
                    tvfirstname.setText(firstname);
                    tvlastname.setText(lastname);
                    tvemail.setText(email);
                    tvphonenumber.setText(phonenumber);
                    try {
                        Picasso.get().load(profileimage).into(imgprofile);
                    } catch (Exception e) {
                        Picasso.get().load(drawable.ic_menu_gallery).into(imgprofile);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}
