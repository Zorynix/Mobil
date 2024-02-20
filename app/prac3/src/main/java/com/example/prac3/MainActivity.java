package com.example.prac3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {

    int testValueInDp = 60;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EditText editText = new EditText(this);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> editText.setText("Button Clicked!"));

        setContentView(R.layout.custom_layout);

        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        constraintLayout.setLayoutParams(layoutParams);

        editText.setText("Programmatically created EditText");
        editText.setWidth(200);
        editText.setHeight(ConstraintLayout.LayoutParams.WRAP_CONTENT);

        setContentView(editText);

    }
}
