package com.example.fastrack_nexon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapterServices extends RecyclerView.Adapter<RecyclerViewAdapterServices.ViewHolder> {

    private ArrayList<Integer> serviceImageUrls = new ArrayList<>();
    private ArrayList<String> serviceName = new ArrayList<>();
    private Context mcontext;

    public RecyclerViewAdapterServices(Context context, ArrayList<Integer> images, ArrayList<String> name) {
        serviceImageUrls = images;
        serviceName = name;
        mcontext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_services, parent, false);
        RecyclerViewAdapterServices.ViewHolder holder = new RecyclerViewAdapterServices.ViewHolder(view1);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(mcontext)
                .asBitmap()
                .load(serviceImageUrls.get(position))
                .into(holder.img_service);

        holder.txt_serviceName.setText(serviceName.get(position));

        holder.parent_recycler_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(mcontext, serviceName.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mcontext, ServicesDetails.class);
                intent.putExtra("image_url", serviceImageUrls.get(position));
                intent.putExtra("image_name", serviceName.get(position));
                mcontext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return serviceName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_service;
        TextView txt_serviceName;
        RelativeLayout parent_recycler_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            img_service = itemView.findViewById(R.id.img_service);
            txt_serviceName = itemView.findViewById(R.id.txt_serviceName);
            parent_recycler_layout = itemView.findViewById(R.id.parent_recycler_layout);
        }
    }
    }