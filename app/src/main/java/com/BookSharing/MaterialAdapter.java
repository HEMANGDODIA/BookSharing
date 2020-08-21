package com.BookSharing;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.BookSharing.ui.Materialdata;
import com.squareup.picasso.Picasso;

import java.util.List;

class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder> {

    private List<Materialdata> materiallist;

    public MaterialAdapter(List<Materialdata> tDlist) { materiallist =tDlist; }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,details, genre,mobileno;
ImageView image_view;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image_view =  view.findViewById(R.id.image_view);
            genre = (TextView) view.findViewById(R.id.genre);
            mobileno = (TextView) view.findViewById(R.id.mobileno);
            details = (TextView) view.findViewById(R.id.details);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.material_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Materialdata movie = materiallist.get(position);
        holder.title.setText(movie.getMaterialname());
        holder.genre.setText(movie.getSubjectName());
        holder.details.setText(movie.getDetails());
        holder.mobileno.setText(movie.getMobileNO());
        Picasso.get().load(movie.getUrl()).into(holder.image_view);
        Log.e("URL", String.valueOf(materiallist.get(position)));
    }

    @Override
    public int getItemCount() { return materiallist.size(); }
}