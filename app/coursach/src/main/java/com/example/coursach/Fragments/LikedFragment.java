package com.example.coursach.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.coursach.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LikedFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_liked, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        ImageView backImg = view.findViewById(R.id.backImg);

        backImg.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> navController.navigate(R.id.mainFragment));

        ImageView liked = view.findViewById(R.id.liked);
        liked.setOnClickListener(v -> navController.navigate(R.id.likedFragment));

        ImageView seen = view.findViewById(R.id.seen);
        seen.setOnClickListener(v -> navController.navigate(R.id.seenFragment));
    }

}
