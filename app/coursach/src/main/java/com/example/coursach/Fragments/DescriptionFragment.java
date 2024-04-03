package com.example.coursach.Fragments;

import android.annotation.SuppressLint;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.coursach.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DescriptionFragment extends Fragment {

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_description, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView backImg = view.findViewById(R.id.backImgSeen);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        backImg.setOnClickListener(v -> {
            navController.navigateUp();
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            navController.navigate(R.id.mainFragment);
        });

        ImageView liked = view.findViewById(R.id.liked);
        liked.setOnClickListener(v -> {
            navController.navigate(R.id.likedFragment);
        });

        ImageView seen = view.findViewById(R.id.seen);
        seen.setOnClickListener(v -> {
            navController.navigate(R.id.descriptionFragment);
        });
    }
}
