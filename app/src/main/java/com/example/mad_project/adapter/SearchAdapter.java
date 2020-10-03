package com.example.mad_project.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;
import com.example.mad_project.model.Parks;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder{
    public TextView town,address;
    Button btn;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);

        town = (TextView)itemView.findViewById(R.id.town);
        address = (TextView)itemView.findViewById(R.id.address);
    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context;
    private List<Parks> parks;

    public SearchAdapter(Context context, List<Parks> parks) {
        this.context = context;
        this.parks = parks;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        holder.town.setText(parks.get(position).getTown());
        holder.address.setText(parks.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return parks.size();
    }
}
