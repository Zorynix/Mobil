package com.example.coursach.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coursach.Adapter.FilmListAdapter;
import com.example.coursach.Domain.ListFilm;
import com.example.coursach.R;
import com.google.gson.Gson;

import java.util.Objects;

public class MainFragment extends Fragment {

    private RecyclerView.Adapter adapterNewMovies, adapterUpComing;
    private RecyclerView recyclerViewNewMovies, recyclerViewUpComing;
    private RequestQueue mRequestQueue;
    private ProgressBar loading1, loading2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        sendRequest1();
        sendRequest2();
    }

    private void initView(View view) {
        recyclerViewNewMovies = view.findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewUpComing = view.findViewById(R.id.view2);
        recyclerViewUpComing.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        loading1 = view.findViewById(R.id.loading1);
        loading2 = view.findViewById(R.id.loading2);
    }

    private void sendRequest1() {
        mRequestQueue = Volley.newRequestQueue(requireContext());
        loading1.setVisibility(View.VISIBLE);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterNewMovies = new FilmListAdapter(items);
            recyclerViewNewMovies.setAdapter(adapterNewMovies);
        }, error -> loading1.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequest2() {
        mRequestQueue = Volley.newRequestQueue(requireContext());
        loading2.setVisibility(View.VISIBLE);
        StringRequest mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterUpComing = new FilmListAdapter(items);
            recyclerViewUpComing.setAdapter(adapterUpComing);
        }, error -> loading2.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest2);
    }
}