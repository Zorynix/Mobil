package com.example.prac2;


import static com.example.prac2.MainActivity.KEY;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class TestActivity  extends AppCompatActivity{

    private Button switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        switchButton = findViewById(R.id.toggleButton);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String str = bundle.getString(KEY);

        assert str != null;

        //Toast.makeText(this,, Toast.LENGTH_LONG).show();
        Snackbar.make(findViewById(R.id.root), str,Snackbar.LENGTH_SHORT).show();

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Запускаем MainActivity
                Intent intent = new Intent(TestActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.KEY, ((EditText) findViewById(R.id.editText)).getText().toString());
                startActivity(intent);
            }
        });

    }

}
