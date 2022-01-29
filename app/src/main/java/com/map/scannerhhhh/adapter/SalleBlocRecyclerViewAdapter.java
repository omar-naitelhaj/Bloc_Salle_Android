package com.map.scannerhhhh.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.map.scannerhhhh.R;
import com.map.scannerhhhh.model.Salle;

import java.util.ArrayList;


public class SalleBlocRecyclerViewAdapter extends RecyclerView.Adapter<SalleBlocRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Salle> listItems;
    private Context context;


    public SalleBlocRecyclerViewAdapter(Context context, ArrayList<Salle> listItems) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salle, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Salle listItem = listItems.get(position);
        holder.name.setText(listItem.getName());
        holder.type.setText(listItem.getType());
        holder.bloc.setText(listItem.getBlocName());
        if (listItem.getOccupied() == 1){
            holder.salleItem.setBackgroundColor(Color.parseColor("#e6c8c8"));
            holder.occupiedPhoto.setImageResource(R.mipmap.x_mark);
        }
        if (listItem.getOccupied() == 0){
            holder.salleItem.setBackgroundColor(Color.parseColor("#c8e6c8"));
            holder.occupiedPhoto.setImageResource(R.mipmap.available);
        }

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout salleItem;
        public TextView name;
        public TextView type;
        public TextView bloc;
        public ImageView occupiedPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            salleItem = (RelativeLayout) itemView.findViewById(R.id.salleItem);
            name = (TextView) itemView.findViewById(R.id.name);
            type = (TextView) itemView.findViewById(R.id.type);
            bloc = (TextView) itemView.findViewById(R.id.bloc);
            occupiedPhoto = (ImageView) itemView.findViewById(R.id.occupiedPhoto);


        }
    }
}

