package com.example.prac11;

import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class ProgressFragment extends Fragment {
    int[] integers=null;
    ProgressBar indicatorBar;
    TextView statusView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        integers = new int[100];
        for(int i=0;i<100;i++) {
            integers[i] = i + 1;
        }
        indicatorBar = view.findViewById(R.id.indicator);
        statusView = view.findViewById(R.id.statusView);
        Button btnFetch = view.findViewById(R.id.progressBtn);
        btnFetch.setOnClickListener(v -> new ProgressTask().execute());
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    class ProgressTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... unused) {
            for (int i = 0; i<integers.length;i++) {
                publishProgress(i);
                SystemClock.sleep(400);
            }
            return null;
        }
        @SuppressLint("SetTextI18n")
        @Override
        protected void onProgressUpdate(Integer... items) {
            indicatorBar.setProgress(items[0]+1);
            statusView.setText("Статус: " + (items[0] + 1));
        }
        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(getActivity(), "Задача завершена",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }
}