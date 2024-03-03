package com.example.prac9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class StateAdapter extends
        RecyclerView.Adapter<StateAdapter.ViewHolder>{
    interface OnStateClickListener{
        void onStateClick(State state, int position);
    }
    private final OnStateClickListener onClickListener;
    private final LayoutInflater inflater;
    private final List<State> states;
    StateAdapter(Context context, List<State> states, OnStateClickListener
            onClickListener) {
        this.onClickListener = onClickListener;
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
        State state = states.get(position);
        holder.flagView.setImageResource(state.getFlagResource());
        holder.nameView.setText(state.getName());
        holder.capitalView.setText(state.getCapital());
        holder.itemView.setOnClickListener(v -> onClickListener.onStateClick(state, position));
    }
    @Override
    public int getItemCount() {
        return states.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        final TextView nameView, capitalView;
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.flag);
            nameView = view.findViewById(R.id.name);
            capitalView = view.findViewById(R.id.capital);
        }
    }
}


//public class StateAdapter extends ArrayAdapter<State> {
//    private final LayoutInflater inflater;
//    private final int layout;
//    private final List<State> states;
//    public StateAdapter(Context context, int resource, List<State> states) {
//        super(context, resource, states);
//        this.states = states;
//        this.layout = resource;
//        this.inflater = LayoutInflater.from(context);
//    }
//    @NonNull
//    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//        ViewHolder viewHolder;
//        if(convertView==null){
//            convertView = inflater.inflate(this.layout, parent, false);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        }
//        else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        State state = states.get(position);
//        viewHolder.imageView.setImageResource(state.getFlagResource());
//        viewHolder.nameView.setText(state.getName());
//        viewHolder.capitalView.setText(state.getCapital());
//        return convertView;
//    }
//    private static class ViewHolder {
//        final ImageView imageView;
//        final TextView nameView, capitalView;
//        ViewHolder(View view){
//            imageView = view.findViewById(R.id.flag);
//            nameView = view.findViewById(R.id.name);
//            capitalView = view.findViewById(R.id.capital);
//        }
//    }
//}
