package com.projects.anoop.avsolutions.touristattractionapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.projects.anoop.avsolutions.touristattractionapp.CustomAdapter;
import com.projects.anoop.avsolutions.touristattractionapp.R;
import com.projects.anoop.avsolutions.touristattractionapp.model.DataItem;
import com.projects.anoop.avsolutions.touristattractionapp.utils.CustomJsonReader;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<DataItem> dataItems;
    ListView listView;
    CustomAdapter itemsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView) root.findViewById(R.id.listView);

        CustomJsonReader jsonReader = new CustomJsonReader(getContext());

        ArrayList<DataItem> dataItems = DataItem.fromJson(jsonReader.getDataItemsJSONArray());

        itemsAdapter = new CustomAdapter(getContext(),dataItems);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView id_ = view.findViewById(R.id.tv_id);

                Toast.makeText(getContext(),id_.getText().toString(),Toast.LENGTH_SHORT).show();


            }
        });

        return root;
    }
}