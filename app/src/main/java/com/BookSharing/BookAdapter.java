package com.BookSharing;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.BookSharing.ui.Bookdata;
import com.BookSharing.ui.Materialdata;
import com.squareup.picasso.Picasso;

import java.util.List;

class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<Bookdata> BookList;

    public BookAdapter(List<Bookdata> tDlist) {
        BookList=tDlist;
    }

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
                .inflate(R.layout.notes_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bookdata movie = BookList.get(position);
        holder.title.setText(movie.getMaterialname());
        holder.genre.setText(movie.getSubjectName());
        holder.details.setText(movie.getDetails());
        holder.mobileno.setText(movie.getMobileNO());
        Picasso.get().load(movie.getUrl()).into(holder.image_view);
        Log.e("URL", String.valueOf(BookList.get(position)));
    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }
}