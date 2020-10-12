package com.example.mad_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mad_project.model.Park;

import java.util.List;

public class ParkAdapter extends ArrayAdapter<Park> {
    int resource;
    Context context;
    List<Park> parks;

    ParkAdapter( Context context, int resource,List<Park> parks) {
        super(context, resource,parks);
        this.resource = resource;
        this.context = context;
        this.parks = parks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView email = row.findViewById(R.id.emailid);
        TextView town = row.findViewById(R.id.stownid);
        TextView address = row.findViewById(R.id.addressid);
        TextView mobile = row.findViewById(R.id.mobileid);
        TextView description = row.findViewById(R.id.discripid);

        Park park = parks.get(position);
        email.setText(park.getEmail());
        town.setText(park.getTown());
        address.setText(park.getAddress());
        mobile.setText(park.getMobile());
        description.setText(park.getDescription());
        return row;

    }
}
