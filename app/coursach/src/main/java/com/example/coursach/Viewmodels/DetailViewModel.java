package com.example.coursach.Viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coursach.Domain.FilmItem;
import com.google.gson.Gson;

public class DetailViewModel extends AndroidViewModel {
    private final MutableLiveData<FilmItem> _currentFilm = new MutableLiveData<>();
    public LiveData<FilmItem> currentFilm = _currentFilm;
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;
    private final RequestQueue requestQueue;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
    }

    public void fetchFilm(int idFilm) {
        _isLoading.setValue(true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm,
                response -> {
                    Gson gson = new Gson();
                    FilmItem film = gson.fromJson(response, FilmItem.class);
                    _currentFilm.setValue(film);
                    _isLoading.setValue(false);
                },
                error -> {
                    _isLoading.setValue(false);
                });

        requestQueue.add(stringRequest);
    }
}

