package com.example.prac8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Date;

public class FragmentContent extends Fragment {
    public FragmentContent(){
        super(R.layout.fragment_content);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button updateButton = view.findViewById(R.id.updateButton);
        TextView updateBox = view.findViewById(R.id.dateTextView);
        updateButton.setOnClickListener(v -> {
            String curDate = new Date().toString();
            updateBox.setText(curDate);
        });
    }
}
