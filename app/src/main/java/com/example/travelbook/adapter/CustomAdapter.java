package com.example.travelbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.travelbook.R;
import com.example.travelbook.model.PlaceModel;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<PlaceModel> {

    ArrayList<PlaceModel> placeList;
    Context context;

    public CustomAdapter(@NonNull Context context, ArrayList<PlaceModel> placeList) {
        super(context, R.layout.custom_list_row, placeList);
        this.placeList = placeList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View customView = layoutInflater.inflate(R.layout.custom_list_row,parent,false);
        TextView nameTextView = customView.findViewById(R.id.nameTextView);
        nameTextView.setText(placeList.get(position).placeName);

        return customView;
    }
}
