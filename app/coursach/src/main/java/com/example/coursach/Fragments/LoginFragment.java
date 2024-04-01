package com.example.coursach.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.coursach.R;

public class LoginFragment extends Fragment {

    private EditText userEdt, passEdt;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private CheckBox rememberMeChk;


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

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initView(view);

        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = requireActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkLogin();
    }

    private void checkLogin() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainFragment);
        }
    }

    private void initView(View view) {
        userEdt = view.findViewById(R.id.editTextUsername);
        passEdt = view.findViewById(R.id.editTextPassword);
        Button loginBtn = view.findViewById(R.id.loginBtn);
        rememberMeChk = view.findViewById(R.id.checkboxRememberMe);

        TextView registerText = view.findViewById(R.id.register);
        registerText.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment));

        loginBtn.setOnClickListener(v -> {
            String email = userEdt.getText().toString().trim();
            String password = passEdt.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                showCustomSnackbar("Пожалуйста, введите ваш логин и пароль.");
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity(), task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null && user.isEmailVerified()) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putBoolean("isLoggedIn", rememberMeChk.isChecked());
                                    editor.apply();
                                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment);
                                } else {
                                    showCustomSnackbar("Пожалуйста, подтвердите ваш email перед входом в приложение.");
                                }
                            } else {
                                showCustomSnackbar("Ошибка аутентификации!");
                            }
                        });
            }
        });
    }
}
