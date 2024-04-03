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

import com.example.coursach.Adapter.FilmListAdapter;
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
import java.util.Objects;

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
        seen.setOnClickListener(v -> navController.navigate(R.id.descriptionFragment));

        getLikedFilmTitles();
    }

    private void getLikedFilmTitles() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance(getResources().getString(R.string.url)).getReference("users").child(userId).child("favorites");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<String> filmTitles = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        FilmItem film = dataSnapshot.getValue(FilmItem.class);
                        if (film != null) {
                            filmTitles.add(film.getTitle());
                        }
                    }
                    updateRecyclerView(filmTitles);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("LikedFragment", "Database Error: " + error.getMessage());
                }
            });
        }
    }

    private void updateRecyclerView(List<String> filmTitles) {
        RecyclerView recyclerView = requireView().findViewById(R.id.recyclerViewLiked);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        LikedFilmsAdapter adapter = new LikedFilmsAdapter(filmTitles, position -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            Bundle bundle = new Bundle();
            // This is a placeholder for demonstration. Replace position with your actual film ID.
            // For instance, if your FilmItem or the way you retrieve films includes IDs, use those instead.
            bundle.putInt("id", position); // Assuming IDs start from 1 for demonstration purposes.
            navController.navigate(R.id.action_likedFragment_to_detailFragment, bundle);
        });

        recyclerView.setAdapter(adapter);
    }
}

