package com.example.prac7;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prac7.databinding.TestActivityBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class TestActivity  extends AppCompatActivity{
    private TestActivityBinding binding;

//    static final String AGE_KEY = "AGE";
//    static final String ACCESS_MESSAGE="ACCESS_MESSAGE";
//    ActivityResultLauncher<Intent> mStartForResult =
//            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                    new ActivityResultCallback<ActivityResult>() {
//                        @Override
//                        public void onActivityResult(ActivityResult result) {
//                            TextView textView = findViewById(R.id.textView);
//                            if(result.getResultCode() == Activity.RESULT_OK){
//                                Intent intent = result.getData();
//                                assert intent != null;
//                                String accessMessage =
//                                        intent.getStringExtra(ACCESS_MESSAGE);
//                                textView.setText(accessMessage);
//                            }
//                            else{
//                                textView.setText("Ошибка доступа");
//                            }
//                        }
//                    });
//    public void onClick(View view) {
//        // получаем введенный возраст
//        EditText ageBox = findViewById(R.id.age);
//        String age = ageBox.getText().toString();
//        Intent intent = new Intent(this, PracticeActivity.class);
//        intent.putExtra(AGE_KEY, age);
//        mStartForResult.launch(intent);

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
