package com.example.coursach.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coursach.Adapter.FilmListAdapter;
import com.example.coursach.Domain.ListFilm;
import com.example.coursach.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;


public class MainFragment extends Fragment {

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

        EditText editTextSearch = view.findViewById(R.id.editTextText);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString();
                if (!searchText.isEmpty()) {
                    searchMovies(searchText);
                }
            }
        });
    }

    private void searchMovies(String query) {
        String searchUrl = "https://moviesapi.ir/api/v1/movies?q=" + query;
        loading1.setVisibility(View.VISIBLE);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, searchUrl, response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response, ListFilm.class);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            FilmListAdapter adapter = new FilmListAdapter(items, navController);
            recyclerViewNewMovies.setAdapter(adapter);
        }, error -> loading1.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequest1() {
        mRequestQueue = Volley.newRequestQueue(requireContext());
        loading1.setVisibility(View.VISIBLE);
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);

            ListFilm items = gson.fromJson(response, ListFilm.class);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            FilmListAdapter adapter = new FilmListAdapter(items, navController);
            recyclerViewNewMovies.setAdapter(adapter);
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
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            FilmListAdapter adapter = new FilmListAdapter(items, navController);
            recyclerViewUpComing.setAdapter(adapter);
        }, error -> loading2.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest2);
    }
}
