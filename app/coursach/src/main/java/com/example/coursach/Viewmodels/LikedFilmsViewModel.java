package com.example.coursach.Viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coursach.Domain.FilmItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LikedFilmsViewModel extends ViewModel {

    private final MutableLiveData<List<FilmItem>> likedFilms = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<FilmItem>> getLikedFilms() {
        return likedFilms;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadLikedFilms(String databaseUrl) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance(databaseUrl)
                    .getReference("users").child(userId).child("favorites");

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<FilmItem> films = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        FilmItem film = dataSnapshot.getValue(FilmItem.class);
                        if (film != null) {
                            films.add(film);
                        }
                    }
                    likedFilms.setValue(films);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    errorMessage.setValue(error.getMessage());
                }
            });
        } else {
            errorMessage.setValue("User is not authenticated");
        }
    }
}

