package com.example.prac7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prac7.databinding.PracticeActivityBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class PracticeActivity extends AppCompatActivity {

    private PracticeActivityBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = PracticeActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            TextView ageView = findViewById(R.id.ageView);
//            String age = extras.getString(TestActivity.AGE_KEY);
//            ageView.setText("Возраст: " + age);
//        }
//    }
//
//    public void onCancelClick(View v) {
//        setResult(RESULT_CANCELED);
//        finish();
//    }
//    public void onButton1Click(View v) {
//        sendMessage("Доступ разрешен");
//    }
//    public void onButton2Click(View v) {
//        sendMessage("Доступ запрещен");
//    }
//    public void onButton3Click(View v) {
//        sendMessage("Недопустимый возраст");
//    }
//    private void sendMessage(String message){
//        Intent data = new Intent();
//        data.putExtra(TestActivity.ACCESS_MESSAGE, message);
//        setResult(RESULT_OK, data);
//        finish();
//    }


        binding.toggleButton.setOnClickListener(v -> {

            Intent intent = new Intent(PracticeActivity.this, TestActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });

        binding.toggleButton2.setOnClickListener(v -> {

            Intent intent = new Intent(PracticeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });


        TextView textView = new TextView(this);
        textView.setTextSize(26);
        textView.setPadding(16, 16, 16, 16);
    }
}
