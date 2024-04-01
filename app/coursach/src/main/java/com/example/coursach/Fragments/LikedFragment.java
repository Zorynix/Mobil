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
import com.example.coursach.Domain.FilmItem;
import com.example.coursach.Domain.ListFilm;
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

    private RecyclerView recyclerViewLikedMovies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liked, container, false);
    }

    private void initView(View view) {

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        ListFilm items = new ListFilm();
        FilmListAdapter adapter = new FilmListAdapter(items, navController);
        recyclerViewLikedMovies = view.findViewById(R.id.recyclerViewLiked);
        recyclerViewLikedMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewLikedMovies.setAdapter(adapter);
    }

    private void loadFavoriteMovies() {

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        ListFilm items = new ListFilm();
        FilmListAdapter adapter = new FilmListAdapter(items, navController);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Log.d("Authentication", "Пользователь не аутентифицирован.");
            return;
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child("users").child(user.getUid()).child("favorites");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<FilmItem> favoriteMovies = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FilmItem film = snapshot.getValue(FilmItem.class);
                    if (film != null) {
                        favoriteMovies.add(film);
                    }
                }
                adapter.setFilms(favoriteMovies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("loadFavoriteMovies", "Ошибка загрузки избранных фильмов", databaseError.toException());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        loadFavoriteMovies();

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
