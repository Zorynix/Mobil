package com.example.coursach.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.coursach.R;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private EditText editTextEmail;
    private FirebaseAuth mAuth;


    private boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private void showCustomSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(requireView(), "", Snackbar.LENGTH_LONG);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        LayoutInflater inflater = LayoutInflater.from(requireContext());
        @SuppressLint("InflateParams") View customView = inflater.inflate(R.layout.custom_snackbar, null);

        TextView textView = customView.findViewById(R.id.snackbar_text);
        textView.setText(message);

        layout.setPadding(0, 0, 0, 0);
        layout.addView(customView, 0);

        snackbar.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();



        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextEmail.requestFocus();

        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editTextEmail, InputMethodManager.SHOW_IMPLICIT);
        }

        ImageView backImg = view.findViewById(R.id.backImg);
        backImg.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_introFragment));


        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginBtn = view.findViewById(R.id.loginBtn);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && currentUser.isEmailVerified()) {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainFragment);
        }

        loginBtn.setOnClickListener(v -> attemptLogin());

    }

    private void attemptLogin() {
        String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            showCustomSnackbar("Пожалуйста, введите адрес электронной почты.");
            return;
        }

        if (!isEmailValid(email)) {
            showCustomSnackbar("Некорректный адрес электронной почты.");
            return;
        }

        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(task -> {
            boolean isNewUser = Objects.requireNonNull(task.getResult().getSignInMethods()).isEmpty();
            if (!isNewUser) {
                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainFragment);
            } else {
                LoginFragmentDirections.ActionLoginFragmentToPassFragment direction =
                        LoginFragmentDirections.actionLoginFragmentToPassFragment(email);
                Navigation.findNavController(requireView()).navigate(direction);
            }
        }).addOnFailureListener(e -> showCustomSnackbar("Произошла ошибка при попытке входа. Попробуйте снова."));
    }
}
