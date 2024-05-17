package com.example.coursach.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.coursach.Domain.FilmItem;
import com.example.coursach.Fragments.BaseFragment;
import com.example.coursach.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FavoritesUtil {

    public static void toggleFavorite(Context context, FilmItem film, ImageView favoriteImg, Fragment fragment) {
        if (film == null) {
            ((BaseFragment) fragment).showCustomSnackbar(context.getString(R.string.infounav));
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Log.d("Authentication", "Пользователь не аутентифицирован.");
            return;
        }

        String userId = user.getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance(context.getResources().getString(R.string.url)).getReference().child("users").child(userId).child("favorites").child(String.valueOf(film.getId()));

        SharedPreferences prefs = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        boolean isCurrentlyFavorite = prefs.getBoolean("Film_" + film.getId(), false);

        if (isCurrentlyFavorite) {
            mDatabase.removeValue().addOnSuccessListener(aVoid -> {
                ((BaseFragment) fragment).showCustomSnackbar(context.getString(R.string.delfav));
                saveFavoriteStatus(context, film.getId(), false);
                updateFavoriteButtonBackground(favoriteImg, false);
            }).addOnFailureListener(e -> ((BaseFragment) fragment).showCustomSnackbar(context.getString(R.string.errdelfav)));
        } else {
            mDatabase.setValue(film).addOnSuccessListener(aVoid -> {
                ((BaseFragment) fragment).showCustomSnackbar(context.getString(R.string.addfav));
                saveFavoriteStatus(context, film.getId(), true);
                updateFavoriteButtonBackground(favoriteImg, true);
            }).addOnFailureListener(e -> ((BaseFragment) fragment).showCustomSnackbar(context.getString(R.string.erraddfav)));
        }
    }

    public static void saveFavoriteStatus(Context context, int filmId, boolean isFavorite) {
        SharedPreferences prefs = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("Film_" + filmId, isFavorite);
        editor.apply();
    }

    public static void updateFavoriteButtonBackground(ImageView favoriteImg, boolean isFavorite) {
        if (isFavorite) {
            favoriteImg.setBackgroundResource(R.drawable.bg_circle_pink);
        } else {
            favoriteImg.setBackgroundResource(R.drawable.bg_cirlcle_dark);
        }
    }

    public static boolean checkFavoriteStatus(Context context, int filmId) {
        SharedPreferences prefs = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        return prefs.getBoolean("Film_" + filmId, false);
    }
}
