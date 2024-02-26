package com.example.prac6;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prac6.databinding.TestActivityBinding;

import java.io.IOException;
import java.io.InputStream;

public class TestActivity  extends AppCompatActivity{
    private TestActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = TestActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//
//        ImageView imageView = binding.imageView;
//        String filename = "_zhn0mzbk3q.jpg";
//        try(InputStream inputStream =
//                    getApplicationContext().getAssets().open(filename)){
//            Drawable drawable = Drawable.createFromStream(inputStream, null);
//            imageView.setImageDrawable(drawable);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }

        binding.toggleButton.setOnClickListener(v -> {

            // Запускаем MainActivity
            Intent intent = new Intent(TestActivity.this, MainActivity.class);
            startActivity(intent);
        });

        binding.toggleButton2.setOnClickListener(v -> {
            Intent intent = new Intent(TestActivity.this, PracticeActivity.class);
            startActivity(intent);
        });

    }

}
