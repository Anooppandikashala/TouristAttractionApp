package com.projects.anoop.avsolutions.touristattractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.projects.anoop.avsolutions.touristattractionapp.model.DataItem;
import com.projects.anoop.avsolutions.touristattractionapp.session_manager.Session;
import com.projects.anoop.avsolutions.touristattractionapp.utils.CustomJsonReader;

import java.util.ArrayList;

public class TouristDetails extends AppCompatActivity {

    DataItem dataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_details);

        TextView name = findViewById(R.id.name);
        TextView desc = findViewById(R.id.desc);
        TextView contact = findViewById(R.id.contact);
        TextView website = findViewById(R.id.website);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ImageView imageView = findViewById(R.id.thumbnail);

        Intent i = getIntent();
        final int id = i.getIntExtra("id",0);

        final CustomJsonReader jsonReader = new CustomJsonReader(getApplicationContext());

        final ArrayList<DataItem> dataItems_ = DataItem.fromJson(jsonReader.getDataItemsJSONArray());

        for(DataItem item : dataItems_)
        {
            if(item.getId() == id)
            {
                dataItem = item;
                break;
            }
        }

        if(dataItem != null)
        {
            ratingBar.setNumStars(5);
            ratingBar.setMax(5);
            ratingBar.setRating(dataItem.getRating());
            name.setText(dataItem.getName());
            desc.setText(dataItem.getDescription());
            contact.setText(dataItem.getContact());
            website.setText(dataItem.getWebsite());
            Resources resources = getApplicationContext().getResources();
            final int resourceId = resources.getIdentifier(dataItem.getImageAt(0), "drawable", getApplicationContext().getPackageName());

            imageView.setImageResource(resourceId);
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                float rating = ratingBar.getRating();
                jsonReader.writeRatingToJson(id, (int) rating);

            }
        });

    }



    public void logout(View view) {
        Session session = new Session(TouristDetails.this);
        session.logOut();

        Intent intent = new Intent(TouristDetails.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void actionDial(View view) {
        TextView contact = findViewById(R.id.contact);
        String res = contact.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+res));
        startActivity(intent);
    }

    public void actionWeb(View view) {
        TextView website = findViewById(R.id.website);
        String url = website.getText().toString();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void actionBack(View view) {
        super.onBackPressed();
    }
}
