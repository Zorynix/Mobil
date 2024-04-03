package com.example.coursach.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursach.R;

import java.util.List;

public class LikedFilmsAdapter extends RecyclerView.Adapter<LikedFilmsAdapter.ViewHolder> {

    private final List<String> filmTitles;
    private final ItemClickListener clickListener;

    public LikedFilmsAdapter(List<String> filmTitles, ItemClickListener clickListener) {
        this.filmTitles = filmTitles;
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
        holder.filmTitleText.setText((position + 1) + ". " + filmTitles.get(position));
        holder.itemView.setOnClickListener(v -> clickListener.onItemClick(position));
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return filmTitles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView filmTitleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filmTitleText = itemView.findViewById(R.id.filmTitleText);
        }
    }
}
