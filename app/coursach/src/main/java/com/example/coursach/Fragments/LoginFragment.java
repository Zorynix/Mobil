package com.example.coursach.Fragments;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.coursach.R;

public class LoginFragment extends Fragment {

    private EditText userEdt, passEdt;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private CheckBox rememberMeChk;

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
                Toast.makeText(getActivity(), "Пожалуйста, введите ваш логин и пароль", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(getActivity(), "Пожалуйста, подтвердите ваш email перед входом в приложение", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Ошибка аутентификации", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
