package com.example.prac9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
//class ProductAdapter extends ArrayAdapter<Product> {
//    private final LayoutInflater inflater;
//    private final int layout;
//    private final ArrayList<Product> productList;
//
//    ProductAdapter(Context context, int resource, ArrayList<Product> products) {
//        super(context, resource, products);
//        this.productList = products;
//        this.layout = resource;
//        this.inflater = LayoutInflater.from(context);
//    }
//
//    @SuppressLint("SetTextI18n")
//    @NonNull
//    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//        final ViewHolder viewHolder;
//        if (convertView == null) {
//            convertView = inflater.inflate(this.layout, parent, false);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        final Product product = productList.get(position);
//        viewHolder.nameView.setText(product.getName());
//        viewHolder.countView.setText(product.getCount() + " " + product.getUnit());
//        viewHolder.removeButton.setOnClickListener(v -> {
//            int count = product.getCount() - 1;
//            if (count < 0) count = 0;
//            product.setCount(count);
//            viewHolder.countView.setText(count + " " + product.getUnit());
//        });
//        viewHolder.addButton.setOnClickListener(v -> {
//            int count = product.getCount() + 1;
//            product.setCount(count);
//            viewHolder.countView.setText(count + " " + product.getUnit());
//        });
//        return convertView;
//    }
//
//    private static class ViewHolder {
//        final Button addButton, removeButton;
//        final TextView nameView, countView;
//
//        ViewHolder(View view) {
//            addButton = view.findViewById(R.id.addButton);
//            removeButton = view.findViewById(R.id.removeButton);
//            nameView = view.findViewById(R.id.nameView);
//            countView = view.findViewById(R.id.countView);
//        }
//    }
//}