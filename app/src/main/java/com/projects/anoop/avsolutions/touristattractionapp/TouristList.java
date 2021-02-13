package com.projects.anoop.avsolutions.touristattractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.anoop.avsolutions.touristattractionapp.model.DataItem;
import com.projects.anoop.avsolutions.touristattractionapp.session_manager.Session;
import com.projects.anoop.avsolutions.touristattractionapp.utils.CustomJsonReader;

import java.util.ArrayList;

public class TouristList extends AppCompatActivity {

    ListView listView;
    CustomAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_list);

        listView = findViewById(R.id.listView);

        CustomJsonReader jsonReader = new CustomJsonReader(getApplicationContext());

        final ArrayList<DataItem> dataItems = DataItem.fromJson(jsonReader.getDataItemsJSONArray());

        itemsAdapter = new CustomAdapter(getApplicationContext(),dataItems);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                gotoDetailsPage(dataItems.get(position));
            }
        });
    }

    private void gotoDetailsPage(DataItem dataItem) {
        Intent i = new Intent(TouristList.this,TouristDetails.class);
        i.putExtra("id",dataItem.getId());
        startActivity(i);
    }

    public void logout(View view) {
        Session session = new Session(TouristList.this);
        session.logOut();

        Intent intent = new Intent(TouristList.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}
