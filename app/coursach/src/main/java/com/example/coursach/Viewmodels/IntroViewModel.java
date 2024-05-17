package com.example.coursach.Viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IntroViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> _isUserLoggedIn = new MutableLiveData<>();
    public LiveData<Boolean> isUserLoggedIn = _isUserLoggedIn;

    private final MutableLiveData<Boolean> _isNetworkAvailable = new MutableLiveData<>();
    public LiveData<Boolean> isNetworkAvailable = _isNetworkAvailable;

    private final FirebaseAuth mAuth;

    public IntroViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
    }

    public void checkUserStatus() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        _isUserLoggedIn.setValue(currentUser != null && currentUser.isEmailVerified() && isUserLoggedIn());
    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("user_auth", Context.MODE_PRIVATE);
        return sharedPreferences.contains("userId");
    }

    public void checkNetworkStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            Network network = connectivityManager.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                _isNetworkAvailable.setValue(networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)));
            } else {
                _isNetworkAvailable.setValue(false);
            }
        } else {
            _isNetworkAvailable.setValue(false);
        }
    }
}
