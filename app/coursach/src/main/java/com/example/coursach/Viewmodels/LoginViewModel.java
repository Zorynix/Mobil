package com.example.coursach.Viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private final FirebaseAuth mAuth;
    private final MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isNewUser = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
    }

    public LiveData<Boolean> getIsAuthenticated() {
        return isAuthenticated;
    }

    public LiveData<Boolean> getIsNewUser() {
        return isNewUser;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void checkCurrentUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && currentUser.isEmailVerified()) {
            isAuthenticated.setValue(true);
        } else {
            isAuthenticated.setValue(false);
        }
    }

    public void attemptLogin(String email, String emailPattern) {
        if (email.isEmpty()) {
            errorMessage.setValue("Please enter your email.");
            return;
        }

        if (!email.matches(emailPattern)) {
            errorMessage.setValue("Please enter a valid email address.");
            return;
        }

        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(task -> {
            boolean isNewUserResult = task.getResult().getSignInMethods().isEmpty();
            isNewUser.setValue(isNewUserResult);
            isAuthenticated.setValue(!isNewUserResult);
        }).addOnFailureListener(e -> errorMessage.setValue("Error during login attempt."));
    }
}
