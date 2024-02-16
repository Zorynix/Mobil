package com.example.prac1;


import static com.example.prac1.MainActivity.KEY;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prac1.R;
import com.google.android.material.snackbar.Snackbar;

public class TestActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String str = bundle.getString(KEY);

        assert str != null;
        Snackbar.make(findViewById(R.id.root), str,Snackbar.LENGTH_SHORT).show();

    }
}