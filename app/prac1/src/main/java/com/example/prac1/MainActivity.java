package com.example.prac1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prac1.R;


public class MainActivity extends AppCompatActivity{
    private Button btn;
    public static final String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){

            final String test_message = getResources().getString(R.string.TestMessage);

            @Override
            public void onClick(View v) {

                // Создаем явное намерение
                Intent intent = new Intent(MainActivity.this, com.example.prac1.TestActivity.class);
                intent.putExtra(KEY, test_message);

                // Запуск другой активности
                startActivity(intent);
            }
        });
    }
}