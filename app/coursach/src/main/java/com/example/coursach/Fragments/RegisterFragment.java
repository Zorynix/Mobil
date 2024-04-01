package com.example.coursach.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.coursach.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {


    private EditText emailEdt, passEdt;
    private FirebaseAuth mAuth;

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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();
        initView(view);

        return view;
    }

    private boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private void initView(View view) {
        emailEdt = view.findViewById(R.id.editTextUsername);
        passEdt = view.findViewById(R.id.editTextPassword);
        Button registerBtn = view.findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(v -> {
            String email = emailEdt.getText().toString().trim();
            String password = passEdt.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                showCustomSnackbar("Пожалуйста, введите ваш email и пароль.");
            } else if (!isEmailValid(email)) {
                showCustomSnackbar("Некорректный формат email!");
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                sendEmailVerification(view);
                            } else {
                                showCustomSnackbar("Ошибка регистрации!");
                            }
                        });
            }
        });
    }

    private void sendEmailVerification(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            showCustomSnackbar( "Письмо с подтверждением отправлено. Проверьте вашу почту.");
                            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                        } else {
                            showCustomSnackbar("Не удалось отправить письмо с подтверждением.");
                        }
                    });
        }
    }
}
