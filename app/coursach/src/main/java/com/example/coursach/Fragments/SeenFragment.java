package com.example.coursach.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coursach.Adapter.FilmListAdapter;
import com.example.coursach.Domain.FilmItem;
import com.example.coursach.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeenFragment extends Fragment {

    private static final String TAG = "SeenFragment";
    private ProgressBar progressBar;
    private FilmListAdapter adapter;
    private List<FilmItem> viewedFilms;
    private int totalFilmsCount;
    private int loadedFilmsCount;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starting SeenFragment view creation.");
        View view = inflater.inflate(R.layout.fragment_seen, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewSeen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewedFilms = new ArrayList<>();
        adapter = new FilmListAdapter(viewedFilms, Navigation.findNavController(requireActivity(), R.id.nav_host_fragment));
        recyclerView.setAdapter(adapter);

        loadViewedFilms();

        return view;
    }

    private void loadViewedFilms() {
        Log.d(TAG, "loadViewedFilms: Loading viewed films.");
        SharedPreferences prefs = requireActivity().getSharedPreferences("ViewedMovies", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        totalFilmsCount = allEntries.size();
        loadedFilmsCount = 0;

        if (totalFilmsCount == 0) {
            Log.d(TAG, "loadViewedFilms: No viewed films found.");
            progressBar.setVisibility(View.GONE);
        }

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d(TAG, "loadViewedFilms: Processing film: " + entry.getKey());
            if (Boolean.TRUE.equals(entry.getValue())) {
                loadFilmDetails(entry.getKey().replace("Film_", ""));
            }
        }
    }

    private void loadFilmDetails(String filmId) {
        Log.d(TAG, "loadFilmDetails: Loading details for film ID: " + filmId);
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String url = "https://moviesapi.ir/api/v1/movies/" + filmId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    FilmItem film = new Gson().fromJson(response, FilmItem.class);
                    if (film != null) {
                        viewedFilms.add(film);
                        int positionInserted = viewedFilms.size() - 1;
                        adapter.notifyItemInserted(positionInserted);

                        Log.d(TAG, "loadFilmDetails: Received film details for ID: " + filmId);
                        Log.d(TAG, "Film Title: " + film.getTitle());
                        Log.d(TAG, "Film Year: " + film.getYear());
                        Log.d(TAG, "Film Rating: " + film.getImdbRating());
                        Log.d(TAG, "Film Runtime: " + film.getRuntime());
                        Log.d(TAG, "notifyItemInserted: Item inserted at position " + positionInserted);
                    } else {
                        Log.d(TAG, "loadFilmDetails: Film data is null for ID: " + filmId);
                    }
                    checkIfAllDataLoaded();
                },
                error -> {
                    Log.e(TAG, "loadFilmDetails: Error fetching film ID: " + filmId + ", error: " + error.toString());
                    checkIfAllDataLoaded();
                }
        );

        queue.add(stringRequest);
    }


    private void checkIfAllDataLoaded() {
        loadedFilmsCount++;
        if (loadedFilmsCount >= totalFilmsCount) {
            Log.d(TAG, "checkIfAllDataLoaded: All data loaded. Hiding progress bar.");
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: View created.");
        super.onViewCreated(view, savedInstanceState);

        ImageView backImg = view.findViewById(R.id.backImg);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        backImg.setOnClickListener(v -> {
            Log.d(TAG, "onViewCreated: Back button clicked.");
            navController.navigateUp();
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Log.d(TAG, "onViewCreated: FAB clicked.");
            navController.navigate(R.id.mainFragment);
        });

        ImageView liked = view.findViewById(R.id.liked);
        liked.setOnClickListener(v -> {
            Log.d(TAG, "onViewCreated: Liked button clicked.");
            navController.navigate(R.id.likedFragment);
        });

        ImageView seen = view.findViewById(R.id.seen);
        seen.setOnClickListener(v -> {
            Log.d(TAG, "onViewCreated: Seen button clicked.");
            navController.navigate(R.id.seenFragment);
        });
    }
}
