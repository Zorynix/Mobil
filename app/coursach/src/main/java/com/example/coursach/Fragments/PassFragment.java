package com.example.coursach.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.coursach.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class PassFragment extends Fragment {

    private EditText passEdt;
    private FirebaseAuth mAuth;
    private String email;

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
        View view = inflater.inflate(R.layout.fragment_pass, container, false);
        mAuth = FirebaseAuth.getInstance();

        passEdt = view.findViewById(R.id.editTextCode);

        passEdt.requestFocus();

        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(passEdt, InputMethodManager.SHOW_IMPLICIT);
        }

        Button registerBtn = view.findViewById(R.id.registerBtn);

        if (getArguments() != null) {
            email = PassFragmentArgs.fromBundle(getArguments()).getEmail();
        }

        ImageView backImg = view.findViewById(R.id.backImg);
        backImg.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        registerBtn.setOnClickListener(v -> registerUser());

        return view;
    }

    private void registerUser() {
        String password = passEdt.getText().toString().trim();

        if (password.isEmpty()) {
            showCustomSnackbar("Пожалуйста, введите пароль.");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    saveUserAuthState(user.getUid());
                }
                Navigation.findNavController(requireView()).navigate(R.id.action_passFragment_to_mainFragment);
            } else {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(registerTask -> {
                    if (registerTask.isSuccessful()) {
                        FirebaseUser newUser = mAuth.getCurrentUser();
                        if (newUser != null) {
                            saveUserAuthState(newUser.getUid());
                            sendEmailVerification();
                        }
                    } else {
                        showCustomSnackbar("Ошибка при регистрации. Попробуйте снова.");
                    }
                }).addOnFailureListener(e -> showCustomSnackbar("Ошибка при регистрации. Такой пользователь уже существует. Возможно вы ввели неверный пароль"));
            }
        });
    }


    private void saveUserAuthState(String userId) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", userId);
        editor.apply();
    }

    private void sendEmailVerification() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    showCustomSnackbar("Письмо с подтверждением отправлено на вашу почту.");
                    Navigation.findNavController(requireView()).navigate(R.id.action_passFragment_to_mainFragment);
                } else {
                    showCustomSnackbar("Не удалось отправить письмо с подтверждением. Попробуйте снова.");
                }
            });
        } else {
            showCustomSnackbar("Ошибка: пользователь не найден.");
        }
    }
}
