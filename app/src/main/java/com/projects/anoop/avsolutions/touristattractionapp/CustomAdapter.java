package com.projects.anoop.avsolutions.touristattractionapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.anoop.avsolutions.touristattractionapp.model.DataItem;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataItem> {

    public CustomAdapter(Context context, ArrayList<DataItem> dataItem) {
        super(context, 0, dataItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataItem dataItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
        }
        // Lookup view for data population
        TextView tvId = convertView.findViewById(R.id.tv_id);
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvLocation = convertView.findViewById(R.id.tv_location);
        ImageView thumbnail = convertView.findViewById(R.id.thumbnail);

        // Populate the data into the template view using the data object
        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier(dataItem.getImageAt(0), "drawable", getContext().getPackageName());

        tvId.setText(String.valueOf(dataItem.getId()));
        tvName.setText(dataItem.getName());
        tvLocation.setText(dataItem.getLocation());
        thumbnail.setImageResource(resourceId);

        // Return the completed view to render on screen
        return convertView;
    }
}
