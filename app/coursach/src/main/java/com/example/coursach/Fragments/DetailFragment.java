package com.example.coursach.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coursach.Adapter.ImageListAdapter;
import com.example.coursach.Domain.FilmItem;
import com.example.coursach.R;
import com.example.coursach.Utils.FavoritesUtil;
import com.example.coursach.Viewmodels.DetailViewModel;
import com.google.android.material.imageview.ShapeableImageView;

public class DetailFragment extends BaseFragment {

    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieDateTxt, movieSummaryInfo, movieActorsInfo;
    private ShapeableImageView pic1;
    private ImageView pic2, favoriteImg;
    private RecyclerView recyclerView;
    private DetailViewModel detailViewModel;
    private int idFilm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        if (getArguments() != null) {
            idFilm = getArguments().getInt("id", 0);
        }

        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        initView(view);
        observeViewModel();
        detailViewModel.fetchFilm(idFilm);

        return view;
    }

    private void initView(View view) {
        titleTxt = view.findViewById(R.id.movieNameTxt);
        progressBar = view.findViewById(R.id.detailLoading);
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

        backImg.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        favoriteImg.setOnClickListener(v -> {
            FilmItem currentFilm = detailViewModel.currentFilm.getValue();
            if (currentFilm != null) {
                FavoritesUtil.toggleFavorite(requireContext(), currentFilm, favoriteImg, this);
            }
        });
    }

    private void observeViewModel() {
        detailViewModel.currentFilm.observe(getViewLifecycleOwner(), this::updateUI);
        detailViewModel.isLoading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void updateUI(FilmItem filmItem) {
        if (filmItem != null) {
            Glide.with(this)
                    .load(filmItem.getPoster())
                    .into(pic1);

            Glide.with(this)
                    .load(filmItem.getPoster())
                    .into(pic2);

            titleTxt.setText(filmItem.getTitle());
            movieRateTxt.setText(filmItem.getImdbRating());
            movieTimeTxt.setText(filmItem.getRuntime());
            movieDateTxt.setText(filmItem.getReleased());
            movieSummaryInfo.setText(filmItem.getPlot());
            movieActorsInfo.setText(filmItem.getActors());

            if (filmItem.getImages() != null) {
                ImageListAdapter adapterImgList = new ImageListAdapter(filmItem.getImages());
                recyclerView.setAdapter(adapterImgList);
            }

            boolean isFavorite = FavoritesUtil.checkFavoriteStatus(requireContext(), filmItem.getId());
            FavoritesUtil.updateFavoriteButtonBackground(favoriteImg, isFavorite);
        }
    }
}
