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
import com.google.gson.Gson;


public class DetailFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieDateTxt, movieSummaryInfo, movieActorsInfo;
    private NestedScrollView scrollView;
    private int idFilm;
    private ShapeableImageView pic1;
    private ImageView pic2;
    private RecyclerView recyclerView;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });
    }

    private void sendRequest() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(requireContext());
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        StringRequest mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {

            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItem item = gson.fromJson(response, FilmItem.class);

            Glide.with(this)
                    .load(item.getPoster())
                    .into(pic1);

            Glide.with(this)
                    .load(item.getPoster())
                    .into(pic2);

            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieDateTxt.setText(item.getReleased());
            movieSummaryInfo.setText(item.getPlot());
            movieActorsInfo.setText(item.getActors());

            if (item.getImages() != null) {
                ImageListAdapter adapterImgList = new ImageListAdapter(item.getImages());
                recyclerView.setAdapter(adapterImgList);
            }
        }, error -> progressBar.setVisibility(View.GONE));

        mRequestQueue.add(mStringRequest);
    }
}
