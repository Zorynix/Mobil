package com.example.prac2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity{
    private Button btn;
    public static final String KEY = "key";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //final String test_message = getResources().getString(R.string.TestMessage);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        btn=findViewById(R.id.button);

        int sdkVersion = Build.VERSION.SDK_INT;
        String versionName = Build.VERSION.RELEASE;

        final String android = "Android Version:" + versionName + " " + "SDK Version: " + sdkVersion;


       Log.d("On create", "onCreate()");

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                onSaveInstanceState(new Bundle());

                String userInput = editText.getText().toString();

                // Создаем явное намерение
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra(KEY, android);

                // Запуск другой активности
                startActivity(intent);
                //finish();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("On start","onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String userInput = savedInstanceState.getString(KEY);
        editText.setText(userInput);
        Log.d("On restore instance", "Restored user input: " + userInput);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("On restart","onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("On resume","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("On pause","onPause()");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String userInput = editText.getText().toString();
        outState.putString(KEY, userInput);
        Log.d("On save instance", "User input saved: " + userInput);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("On stop","onStop()");
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d("On destroy","onDestroy()");
//    }

}