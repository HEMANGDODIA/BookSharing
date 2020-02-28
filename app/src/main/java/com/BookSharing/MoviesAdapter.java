package com.BookSharing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.BookSharing.ui.Bookdata;
import com.squareup.picasso.Picasso;

import java.util.List;

class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Bookdata> moviesList;

    public MoviesAdapter(List<Bookdata> tDlist) {
        moviesList=tDlist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
ImageView image_view;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image_view =  view.findViewById(R.id.image_view);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bookdata movie = moviesList.get(position);
        holder.title.setText(movie.getAuthersName());
        holder.genre.setText(movie.getBookname());
        holder.year.setText(movie.getDetails());
        Picasso.get().load(movie.getUrl()).into(holder.image_view);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}