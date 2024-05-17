package com.example.coursach.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursach.Adapter.LikedFilmsAdapter;
import com.example.coursach.Domain.FilmItem;
import com.example.coursach.R;
import com.example.coursach.Viewmodels.LikedFilmsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LikedFragment extends Fragment {

    private LikedFilmsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liked, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LikedFilmsViewModel.class);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        ImageView backImg = view.findViewById(R.id.backImg);
        backImg.setOnClickListener(v -> navController.navigateUp());

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> navController.navigate(R.id.mainFragment));

        ImageView liked = view.findViewById(R.id.liked);
        liked.setOnClickListener(v -> navController.navigate(R.id.likedFragment));

        ImageView seen = view.findViewById(R.id.seen);
        seen.setOnClickListener(v -> navController.navigate(R.id.descriptionFragment));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewLiked);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.getLikedFilms().observe(getViewLifecycleOwner(), films -> updateRecyclerView(recyclerView, films, navController));
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show());

        viewModel.loadLikedFilms(getResources().getString(R.string.url));
    }

    private void updateRecyclerView(RecyclerView recyclerView, List<FilmItem> films, NavController navController) {
        LikedFilmsAdapter adapter = new LikedFilmsAdapter(films, filmId -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", filmId);
            navController.navigate(R.id.action_likedFragment_to_detailFragment, bundle);
        });
        recyclerView.setAdapter(adapter);
    }
}
