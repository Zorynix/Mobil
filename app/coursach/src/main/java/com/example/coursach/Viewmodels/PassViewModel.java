package com.example.coursach.Viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PassViewModel extends AndroidViewModel {
    private final FirebaseAuth mAuth;
    private final MutableLiveData<Boolean> _isUserRegistered = new MutableLiveData<>();
    public LiveData<Boolean> isUserRegistered = _isUserRegistered;

    public PassViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                _isUserRegistered.setValue(true);
            } else {
                createNewUser(email, password);
            }
        });
    }

    private void createNewUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                _isUserRegistered.setValue(true);
                sendEmailVerification();
            } else {
                _isUserRegistered.setValue(false);
            }
        }).addOnFailureListener(e -> _isUserRegistered.setValue(false));
    }

    private void sendEmailVerification() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification();
        }
    }
}