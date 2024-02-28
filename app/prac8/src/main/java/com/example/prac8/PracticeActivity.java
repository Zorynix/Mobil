package com.example.prac8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prac8.databinding.PracticeActivityBinding;


public class PracticeActivity extends AppCompatActivity implements ListFragment.OnFragmentSendDataListener{

    private PracticeActivityBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = PracticeActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_container_view, FragmentContent.class, null)
//                    .commit();
//        }

//        binding.toggleButton.setOnClickListener(v -> {
//
//            Intent intent = new Intent(PracticeActivity.this, TestActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(intent);
//        });
//
//        binding.toggleButton2.setOnClickListener(v -> {
//
//            Intent intent = new Intent(PracticeActivity.this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(intent);
//        });

        setContentView(view);

    }

    @Override
    public void onSendData(String selectedItem) {
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isVisible())
            fragment.setSelectedItem(selectedItem);
        else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra(DetailActivity.SELECTED_ITEM, selectedItem);
            startActivity(intent);
        }
    }

}



