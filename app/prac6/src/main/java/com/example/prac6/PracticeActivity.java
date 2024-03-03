package com.example.prac6;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prac6.databinding.PracticeActivityBinding;


public class PracticeActivity extends AppCompatActivity {

    private PracticeActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = PracticeActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        binding.toggleButton.setOnClickListener(v -> {

            Intent intent = new Intent(PracticeActivity.this, TestActivity.class);
            startActivity(intent);
        });

        binding.toggleButton2.setOnClickListener(v -> {

            Intent intent = new Intent(PracticeActivity.this, MainActivity.class);
            startActivity(intent);
        });


        Resources res = getResources();
        String[] languages = res.getStringArray(R.array.languages);
        String allLangs = "";
        for (String lang: languages) {
            allLangs += lang + "\n";
        }
        TextView textView = new TextView(this);
        textView.setText(allLangs);
        textView.setTextColor(1234567899);
        textView.setTextSize(28);
        setContentView(textView);
    }


    //setContentView(view);
}
