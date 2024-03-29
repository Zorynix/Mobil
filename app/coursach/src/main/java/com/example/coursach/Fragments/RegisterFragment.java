package com.example.coursach.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.coursach.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {

    private EditText emailEdt, passEdt;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();
        initView(view);

        return view;
    }

    private void initView(View view) {
        emailEdt = view.findViewById(R.id.editTextUsername);
        passEdt = view.findViewById(R.id.editTextPassword);
        Button registerBtn = view.findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(v -> {
            String email = emailEdt.getText().toString().trim();
            String password = passEdt.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Пожалуйста, введите ваш email и пароль", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                sendEmailVerification(view);
                            } else {
                                Toast.makeText(getActivity(), "Ошибка регистрации", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "Письмо с подтверждением отправлено. Проверьте вашу почту.", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                        } else {
                            Toast.makeText(getActivity(), "Не удалось отправить письмо с подтверждением.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
