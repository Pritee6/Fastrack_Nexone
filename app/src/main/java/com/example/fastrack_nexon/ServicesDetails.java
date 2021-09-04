package com.example.fastrack_nexon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ServicesDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_details);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){

            Integer imageUrl = getIntent().getIntExtra("image_url", R.drawable.logo);
            String imageName = getIntent().getStringExtra("image_name");

            setImage(imageUrl, imageName);
        }
    }


    private void setImage(Integer imageUrl, String imageName){

        TextView txt_serviceTitle = findViewById(R.id.txt_serviceTitle);
        txt_serviceTitle.setText(imageName);

        ImageView img_servicesIcon = findViewById(R.id.img_servicesIcon);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(img_servicesIcon);
    }

}