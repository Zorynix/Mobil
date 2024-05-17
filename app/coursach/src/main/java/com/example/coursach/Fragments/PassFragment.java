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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.coursach.R;
import com.example.coursach.Viewmodels.PassViewModel;

public class PassFragment extends BaseFragment {

    private EditText passEdt;
    private PassViewModel passViewModel;
    private String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pass, container, false);

        // Получение аргументов
        if (getArguments() != null) {
            email = PassFragmentArgs.fromBundle(getArguments()).getEmail();
        }

        // Инициализация ViewModel
        passViewModel = new ViewModelProvider(this).get(PassViewModel.class);

        initView(view);
        observeViewModel();

        return view;
    }

    private void initView(View view) {
        passEdt = view.findViewById(R.id.editTextCode);
        Button registerBtn = view.findViewById(R.id.registerBtn);
        ImageView backImg = view.findViewById(R.id.backImg);

        passEdt.requestFocus();
        showKeyboard();

        backImg.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
        registerBtn.setOnClickListener(v -> registerUser());
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(passEdt, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void registerUser() {
        String password = passEdt.getText().toString().trim();

        if (password.isEmpty()) {
            showCustomSnackbar(getString(R.string.plspass));
            return;
        }

        passViewModel.registerUser(email, password);
    }

    private void observeViewModel() {
        passViewModel.isUserRegistered.observe(getViewLifecycleOwner(), isRegistered -> {
            if (isRegistered != null && isRegistered) {
                navigateToMainFragment();
            } else {
                showCustomSnackbar(getString(R.string.errreg));
            }
        });
    }

    private void navigateToMainFragment() {
        Navigation.findNavController(requireView()).navigate(R.id.action_passFragment_to_mainFragment);
    }
}
