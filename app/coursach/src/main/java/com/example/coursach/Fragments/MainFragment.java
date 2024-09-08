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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursach.Adapter.FilmListAdapter;
import com.example.coursach.Domain.ListFilm;
import com.example.coursach.R;
import com.example.coursach.Viewmodels.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainFragment extends Fragment {

    private MainViewModel viewModel;
    private RecyclerView recyclerViewNewMovies, recyclerViewUpComing;
    private ProgressBar loading1, loading2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initView(view);
        setupObservers();
        viewModel.loadNewMovies();
        viewModel.loadUpcomingMovies();

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> navController.navigate(R.id.mainFragment));

        ImageView liked = view.findViewById(R.id.liked);
        liked.setOnClickListener(v -> navController.navigate(R.id.descriptionFragment));

        ImageView seen = view.findViewById(R.id.seen);
        seen.setOnClickListener(v -> navController.navigate(R.id.likedFragment));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null || !currentUser.isEmailVerified()) {
            Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment_to_loginFragment);
        }
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString();
                if (!searchText.isEmpty()) {
                    viewModel.searchMovies(searchText);
                }
            }
        });
    }

    private void setupObservers() {
        viewModel.getNewMovies().observe(getViewLifecycleOwner(), this::updateRecyclerViewNewMovies);
        viewModel.getUpcomingMovies().observe(getViewLifecycleOwner(), this::updateRecyclerViewUpcomingMovies);
        viewModel.getIsLoadingNewMovies().observe(getViewLifecycleOwner(), isLoading -> loading1.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        viewModel.getIsLoadingUpcomingMovies().observe(getViewLifecycleOwner(), isLoading -> loading2.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRecyclerViewNewMovies(ListFilm items) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        FilmListAdapter adapter = new FilmListAdapter(items, navController);
        recyclerViewNewMovies.setAdapter(adapter);
    }

    private void updateRecyclerViewUpcomingMovies(ListFilm items) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        FilmListAdapter adapter = new FilmListAdapter(items, navController);
        recyclerViewUpComing.setAdapter(adapter);
    }
}
