package com.example.coursach.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.coursach.R;
import com.example.coursach.Viewmodels.IntroViewModel;

public class IntroFragment extends BaseFragment {

    private IntroViewModel introViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        introViewModel = new ViewModelProvider(this).get(IntroViewModel.class);
        introViewModel.checkUserStatus();
        introViewModel.checkNetworkStatus();

        observeViewModel(view);
        setupStartButton(view);
    }

    private void setupStartButton(View view) {
        Button getStartBtn = view.findViewById(R.id.getStart);
        getStartBtn.setOnClickListener(v -> {
            introViewModel.checkNetworkStatus();
        });
    }

    private void observeViewModel(View view) {
        introViewModel.isUserLoggedIn.observe(getViewLifecycleOwner(), isUserLoggedIn -> {
            if (isUserLoggedIn) {
                Navigation.findNavController(view).navigate(R.id.action_introFragment_to_mainFragment);
            }
        });

        introViewModel.isNetworkAvailable.observe(getViewLifecycleOwner(), isNetworkAvailable -> {
            if (isNetworkAvailable) {
                Navigation.findNavController(requireView()).navigate(R.id.action_introFragment_to_loginFragment);
            } else {
                showCustomSnackbar(getString(R.string.no_internet));
            }
        });
    }
}
