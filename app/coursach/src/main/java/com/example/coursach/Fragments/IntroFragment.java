package com.example.coursach.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.coursach.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IntroFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (checkUserStatus()) {
            Navigation.findNavController(view).navigate(R.id.action_introFragment_to_mainFragment);
        } else {
            setupStartButton(view);
        }
    }

    private void setupStartButton(View view) {
        Button getStartBtn = view.findViewById(R.id.getStart);

        getStartBtn.setOnClickListener(v -> {
            if (isNetworkAvailable()) {
                Navigation.findNavController(requireView()).navigate(R.id.action_introFragment_to_loginFragment);
            } else {
                showCustomSnackbar("Internet connection is required");
            }
        });
    }

    private boolean checkUserStatus() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser != null && currentUser.isEmailVerified() && isUserLoggedIn();
    }

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_auth", Context.MODE_PRIVATE);
        return sharedPreferences.contains("userId");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
