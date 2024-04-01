package com.example.coursach.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.coursach.Adapter.ImageListAdapter;
import com.example.coursach.Domain.FilmItem;
import com.example.coursach.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;



public class DetailFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieDateTxt, movieSummaryInfo, movieActorsInfo;
    private NestedScrollView scrollView;
    private int idFilm;
    private ShapeableImageView pic1;
    private ImageView pic2, favoriteImg;
    private RecyclerView recyclerView;
    private FilmItem currentFilm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        if (getArguments() != null) {
            idFilm = getArguments().getInt("id", 0);
        }

        initView(view);
        sendRequest();

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.detailFragment), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoriteImg.setOnClickListener(v -> toggleFavorite(currentFilm));
        checkFavoriteStatus();
    }

    private void showCustomSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(requireView(), "", Snackbar.LENGTH_LONG);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View customView = inflater.inflate(R.layout.custom_snackbar, null);

        TextView textView = customView.findViewById(R.id.snackbar_text);
        textView.setText(message);

        layout.setPadding(0, 0, 0, 0);
        layout.addView(customView, 0);

        snackbar.show();
    }

    private void initView(View view) {
        titleTxt = view.findViewById(R.id.movieNameTxt);
        progressBar = view.findViewById(R.id.detailLoading);
        scrollView = view.findViewById(R.id.ScrollViewDetail);
        pic1 = view.findViewById(R.id.posterNormalImg);
        pic2 = view.findViewById(R.id.posterBigImg);
        movieRateTxt = view.findViewById(R.id.movieRateTxt);
        movieTimeTxt = view.findViewById(R.id.movieTimeTxt);
        movieDateTxt = view.findViewById(R.id.movieDateTxt);
        movieSummaryInfo = view.findViewById(R.id.movieSummaryInfo);
        movieActorsInfo = view.findViewById(R.id.movieActorInfo);
        ImageView backImg = view.findViewById(R.id.backImg);
        recyclerView = view.findViewById(R.id.imagesRecyclerView);
        favoriteImg = view.findViewById(R.id.favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
    }

    private void toggleFavorite(FilmItem film) {
        if (film == null) {
            showCustomSnackbar("Информация о фильме недоступна!");
            return;
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Log.d("Authentication", "Пользователь не аутентифицирован.");
            return;
        }

        String userId = user.getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance(getResources().getString(R.string.url)).getReference().child("users").child(userId).child("favorites").child(String.valueOf(film.getId()));
        SharedPreferences prefs = requireActivity().getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        boolean isCurrentlyFavorite = prefs.getBoolean("Film_" + film.getId(), false);

        if (isCurrentlyFavorite) {
            mDatabase.removeValue().addOnSuccessListener(aVoid -> {
                showCustomSnackbar("Удалено из избранного.");
                saveFavoriteStatus(film.getId(), false);
                updateFavoriteButtonBackground(false);
            }).addOnFailureListener(e -> showCustomSnackbar("Ошибка при удалении из избранного."));
        } else {
            mDatabase.setValue(film).addOnSuccessListener(aVoid -> {
                showCustomSnackbar("Добавлено в избранное.");
                saveFavoriteStatus(film.getId(), true);
                updateFavoriteButtonBackground(true);
            }).addOnFailureListener(e -> showCustomSnackbar("Не удалось добавить в избранное."));
        }
    }


    private void saveFavoriteStatus(int filmId, boolean isFavorite) {
        SharedPreferences prefs = requireActivity().getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("Film_" + filmId, isFavorite);
        editor.apply();
    }


    private void updateFavoriteButtonBackground(boolean isFavorite) {
        if (isFavorite) {
            favoriteImg.setBackgroundResource(R.drawable.bg_circle_pink);
        } else {
            favoriteImg.setBackgroundResource(R.drawable.bg_cirlcle_dark);
        }
    }

    private void checkFavoriteStatus() {
        SharedPreferences prefs = requireActivity().getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        boolean isFavorite = prefs.getBoolean("Film_" + idFilm, false);
        updateFavoriteButtonBackground(isFavorite);
    }

    private void sendRequest() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(requireContext());
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        StringRequest mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {

            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            currentFilm = gson.fromJson(response, FilmItem.class);

            if (currentFilm != null) {
                Glide.with(this)
                        .load(currentFilm.getPoster())
                        .into(pic1);

                Glide.with(this)
                        .load(currentFilm.getPoster())
                        .into(pic2);

                titleTxt.setText(currentFilm.getTitle());
                movieRateTxt.setText(currentFilm.getImdbRating());
                movieTimeTxt.setText(currentFilm.getRuntime());
                movieDateTxt.setText(currentFilm.getReleased());
                movieSummaryInfo.setText(currentFilm.getPlot());
                movieActorsInfo.setText(currentFilm.getActors());

                if (currentFilm.getImages() != null) {
                    ImageListAdapter adapterImgList = new ImageListAdapter(currentFilm.getImages());
                    recyclerView.setAdapter(adapterImgList);
                }
            } else {
                showCustomSnackbar("Ошибка при загрузке информации о фильме!");
            }
        }, error -> {
            progressBar.setVisibility(View.GONE);
            showCustomSnackbar("Ошибка при извлечении данных о фильме!");
        });

        mRequestQueue.add(mStringRequest);
    }
}
