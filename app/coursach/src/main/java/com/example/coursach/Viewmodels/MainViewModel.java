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
import com.example.coursach.Domain.ListFilm;
import com.google.gson.Gson;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<ListFilm> newMovies = new MutableLiveData<>();
    private final MutableLiveData<ListFilm> upcomingMovies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoadingNewMovies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoadingUpcomingMovies = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    private final RequestQueue requestQueue;

    public MainViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
    }

    public LiveData<ListFilm> getNewMovies() {
        return newMovies;
    }

    public LiveData<ListFilm> getUpcomingMovies() {
        return upcomingMovies;
    }

    public LiveData<Boolean> getIsLoadingNewMovies() {
        return isLoadingNewMovies;
    }

    public LiveData<Boolean> getIsLoadingUpcomingMovies() {
        return isLoadingUpcomingMovies;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadNewMovies() {
        isLoadingNewMovies.setValue(true);
        String url = "https://moviesapi.ir/api/v1/movies?page=1";
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            newMovies.setValue(gson.fromJson(response, ListFilm.class));
            isLoadingNewMovies.setValue(false);
        }, error -> {
            errorMessage.setValue("Failed to load new movies.");
            isLoadingNewMovies.setValue(false);
        });
        requestQueue.add(request);
    }

    public void loadUpcomingMovies() {
        isLoadingUpcomingMovies.setValue(true);
        String url = "https://moviesapi.ir/api/v1/movies?page=3";
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new Gson();
            upcomingMovies.setValue(gson.fromJson(response, ListFilm.class));
            isLoadingUpcomingMovies.setValue(false);
        }, error -> {
            errorMessage.setValue("Failed to load upcoming movies.");
            isLoadingUpcomingMovies.setValue(false);
        });
        requestQueue.add(request);
    }

    public void searchMovies(String query) {
        isLoadingNewMovies.setValue(true);
        String searchUrl = "https://moviesapi.ir/api/v1/movies?q=" + query;
        StringRequest request = new StringRequest(Request.Method.GET, searchUrl, response -> {
            Gson gson = new Gson();
            newMovies.setValue(gson.fromJson(response, ListFilm.class));
            isLoadingNewMovies.setValue(false);
        }, error -> {
            errorMessage.setValue("Failed to search movies.");
            isLoadingNewMovies.setValue(false);
        });
        requestQueue.add(request);
    }
}
