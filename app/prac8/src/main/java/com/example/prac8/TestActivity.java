package com.example.prac8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prac8.databinding.TestActivityBinding;

public class TestActivity  extends AppCompatActivity{
    private TestActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = TestActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toggleButton.setOnClickListener(v -> {

            // Запускаем MainActivity
            Intent intent = new Intent(TestActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

        binding.toggleButton2.setOnClickListener(v -> {
            Intent intent = new Intent(TestActivity.this, PracticeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });

    }

}
