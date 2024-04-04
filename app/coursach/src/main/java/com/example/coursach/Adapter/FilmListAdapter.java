package com.example.coursach.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.coursach.Domain.FilmItem;
import com.example.coursach.Domain.ListFilm;

import com.example.coursach.R;

import org.w3c.dom.Text;

import java.util.List;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {

    ListFilm items;
    NavController navController;

    public FilmListAdapter(ListFilm items, NavController navController) {
        this.items = items;
        this.navController = navController;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilms(List<FilmItem> films) {
        this.items = (ListFilm) films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film, parent, false);

        return new ViewHolder(inflate);
    }

    public FilmListAdapter(List<FilmItem> movies, NavController navController) {
        this.navController = navController;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FilmListAdapter.ViewHolder holder, int position) {
        holder.titleTxt.setText(items.getData().get(position).getTitle());
        holder.ScoreTxt.setText("" + items.getData().get(position).getImdbRating());

        Glide.with(holder.itemView.getContext())
                .load(items.getData().get(position).getPoster())
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", items.getData().get(position).getId());
            navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return (items != null && items.getData() != null) ? items.getData().size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, ScoreTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleText);
            ScoreTxt = itemView.findViewById(R.id.scoreTxt);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}

