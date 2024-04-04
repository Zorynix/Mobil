package com.example.coursach.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursach.Adapter.LikedFilmsAdapter;
import com.example.coursach.Domain.FilmItem;
import com.example.coursach.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

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

        backImg.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> navController.navigate(R.id.mainFragment));

        ImageView liked = view.findViewById(R.id.liked);
        liked.setOnClickListener(v -> navController.navigate(R.id.likedFragment));

        ImageView seen = view.findViewById(R.id.seen);
        seen.setOnClickListener(v -> navController.navigate(R.id.descriptionFragment));

        getLikedFilmTitles();
    }

    private void getLikedFilmTitles() {
        Log.d("LikedFragment", "Entering getLikedFilmTitles");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            Log.d("LikedFragment", "UserID: " + userId);
            DatabaseReference databaseReference = FirebaseDatabase.getInstance(getResources().getString(R.string.url)).getReference("users").child(userId).child("favorites");
            Log.d("LikedFragment", "DatabaseReference: " + databaseReference);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<FilmItem> films = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Log.d("LikedFragment", "DataSnapshot: " + dataSnapshot.getKey());
                        FilmItem film = dataSnapshot.getValue(FilmItem.class);
                        if (film != null) {
                            films.add(film);
                            Log.d("LikedFragment", "Film added: " + film.getTitle());
                        }
                    }
                    updateRecyclerView(films);
                    Log.d("LikedFragment", "Exiting getLikedFilmTitles");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("LikedFragment", "Database Error: " + error.getMessage());
                }
            });
        } else {
            Log.d("LikedFragment", "User is null");
        }
    }

    private void updateRecyclerView(List<FilmItem> films) {
        Log.d("LikedFragment", "Updating RecyclerView with " + films.size() + " items");
        RecyclerView recyclerView = requireView().findViewById(R.id.recyclerViewLiked);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        LikedFilmsAdapter adapter = new LikedFilmsAdapter(films, filmId -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            Bundle bundle = new Bundle();
            bundle.putInt("id", filmId);
            Log.e("LikedFragment", "Film ID: " + filmId);

            navController.navigate(R.id.action_likedFragment_to_detailFragment, bundle);
        });

        recyclerView.setAdapter(adapter);
    }
}

