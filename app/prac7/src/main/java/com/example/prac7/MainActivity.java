package com.example.prac7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prac7.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // неявное намерение
        // Intent intent = new Intent(Intent.ACTION_VIEW);
        // String str = "https://mirea.ru";
        //  intent.setData(Uri.parse(str));
        //  startActivity(intent);

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener(v -> {

            // Создаем явное намерение
            Intent intent = new Intent(MainActivity.this, TestActivity.class);

            // Запуск другой активности
            startActivity(intent);
        });
    }
}