package com.example.coursach.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.coursach.R;
import com.example.coursach.Viewmodels.LoginViewModel;

public class LoginFragment extends Fragment {

    private EditText editTextEmail;
    private LoginViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        Button loginBtn = view.findViewById(R.id.loginBtn);

        viewModel.checkCurrentUser();

        viewModel.getIsAuthenticated().observe(getViewLifecycleOwner(), isAuthenticated -> {
            if (isAuthenticated) {
                Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_mainFragment);
            }
        });

        viewModel.getIsNewUser().observe(getViewLifecycleOwner(), isNewUser -> {
            if (isNewUser != null && isNewUser) {
                String email = editTextEmail.getText().toString().trim();
                LoginFragmentDirections.ActionLoginFragmentToPassFragment direction =
                        LoginFragmentDirections.actionLoginFragmentToPassFragment(email);
                Navigation.findNavController(requireView()).navigate(direction);
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

        loginBtn.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String emailPattern = getString(R.string.pattern);
            viewModel.attemptLogin(email, emailPattern);
        });
    }
}
