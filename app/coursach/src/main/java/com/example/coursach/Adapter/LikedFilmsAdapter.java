package com.example.coursach.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursach.Domain.FilmItem;
import com.example.coursach.R;

import java.util.List;

public class LikedFilmsAdapter extends RecyclerView.Adapter<LikedFilmsAdapter.ViewHolder> {

    private final List<FilmItem> films;
    private final OnFilmClickListener clickListener;

    public LikedFilmsAdapter(List<FilmItem> films, OnFilmClickListener clickListener) {
        this.films = films;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_liked_film, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FilmItem film = films.get(position);
        holder.filmTitleText.setText((position + 1) + ". " + film.getTitle());
        holder.itemView.setOnClickListener(v -> clickListener.onFilmClick(film.getId()));
    }

    public interface OnFilmClickListener {
        void onFilmClick(int filmId);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView filmTitleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filmTitleText = itemView.findViewById(R.id.filmTitleText);
        }
    }
}
