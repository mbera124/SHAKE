package com.example.josephnyangaresi.shake;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Recipe extends AppCompatActivity implements ValueEventListener {

    private ImageView imgrecipe;
    private EditText etrecipename, etrecipedetails;
    private Button btnsave, btngallery, btnback;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
   private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
   private DatabaseReference mRootReference = firebaseDatabase.getReference();
   private DatabaseReference mRecipenameReference = firebaseDatabase.getReference("recipename");
   private DatabaseReference mRecipedetaisReference = firebaseDatabase.getReference("recipedetails");
 //  private DatabaseReference mrecipeimageReference = firebaseDatabase.getReference("recipeimage");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        imgrecipe = findViewById(R.id.imgrecipe);
        etrecipedetails = findViewById(R.id.etrecipedetails);
        etrecipename = findViewById(R.id.etrecipename);
        btngallery = findViewById(R.id.btngallery);
        btnsave = findViewById(R.id.btnsave);
        btnback = findViewById(R.id.btnexit);


        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Recipe.this, MainActivity.class));
                finish();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**  FirebaseDatabase database;
                 Recipe_Database = new Recipe_Database("","","");
                 FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                 DatabaseReference mroot = firebaseDatabase.getReference();
                 database.child("mocha-bea62").push().setValue(etrecipename, etrecipedetails, (DatabaseReference.CompletionListener) imgrecipe);**/
                String recipename = etrecipename.getText().toString();
                mRecipenameReference.setValue(recipename);
                etrecipename.setText("");
                String recipedetails = etrecipedetails.getText().toString();
                mRecipedetaisReference.setValue(recipedetails);
                etrecipedetails.setText("");
                /** String recipeimage = imgrecipe.setImageResource(imgrecipe).toString();
                 mRecipenameReference.setValue(recipeimage);
                 etrecipename.setText("");**/

            }
        });

    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
@Deprecated
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            imageUri = data.getData();
            imgrecipe.setImageURI(imageUri);
        }
}

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.getValue(String.class)!=null){
            String key = dataSnapshot.getKey();
            if (key.equals("recipename")){
                String recipename = dataSnapshot.getValue(String.class);
                etrecipename.setText(recipename);
            }
             if (key.equals("recipedetails")){
                String recipedetails = dataSnapshot.getValue(String.class);
                etrecipedetails.setText(recipedetails);
            }
         /** else if (key.equals("recipeimage")){
                String recipeimage = dataSnapshot.getValue(String.class);
                imgrecipe.setImageURI(Uri.parse(recipeimage));
            }**/
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
    @Override
    protected void onStart(){
        super.onStart();
        mRecipenameReference.addValueEventListener(this);
      //  mrecipeimageReference.addValueEventListener(this);
        mRecipedetaisReference.addValueEventListener(this);
         }
    }




