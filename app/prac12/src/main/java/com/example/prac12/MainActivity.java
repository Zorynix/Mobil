package com.example.prac12;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements Removable {
    //VideoView videoPlayer;

//    MediaPlayer mPlayer;
//    Button playButton, pauseButton, stopButton;
//
//    SeekBar volumeControl;
//    AudioManager audioManager;

//    TextView currentDateTime;
//    Calendar dateAndTime = Calendar.getInstance();
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView phonesList = findViewById(R.id.phonesList);
        ArrayList<String> phones = new ArrayList<>();
        phones.add("Google Pixel");
        phones.add("Huawei P9");
        phones.add("LG G5");
        phones.add("Samsung Galaxy S8");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                phones);
        phonesList.setAdapter(adapter);
        phonesList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedPhone = adapter.getItem(position);
            CustomDialogFragment dialog = new CustomDialogFragment();
            Bundle args = new Bundle();
            args.putString("phone", selectedPhone);
            dialog.setArguments(args);
            dialog.show(getSupportFragmentManager(), "custom");
        });
    }
    @Override
    public void remove(String name) {
        adapter.remove(name);
    }


//        ListView phonesList = findViewById(R.id.phonesList);
//        ArrayList<String> phones = new ArrayList<>();
//        phones.add("Google Pixel");
//        phones.add("Huawei P9");
//        phones.add("LG G5");
//        phones.add("Samsung Galaxy S8");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, phones);
//        phonesList.setAdapter(adapter);
//        phonesList.setOnItemClickListener((parent, view, position, id) -> {
//            String selectedPhone = adapter.getItem(position);
//            CustomDialogFragment dialog = new CustomDialogFragment();
//            Bundle args = new Bundle();
//            args.putString("phone", selectedPhone);
//            dialog.setArguments(args);
//            dialog.show(getSupportFragmentManager(), "custom");
//        });
    }

//        currentDateTime = findViewById(R.id.currentDateTime);
//        setInitialDateTime();
//        ImageView img = findViewById(R.id.animationView);
//        img.setImageResource(R.drawable.a100);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.common_animation);
//        img.startAnimation(animation);

//        ImageView img = findViewById(R.id.animationView);
//        img.setBackgroundResource(R.drawable.rab);
//        AnimationDrawable frameAnimation = (AnimationDrawable)
//                img.getBackground();
//        // по нажатию на ImageView
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // запускаем анимацию
//                frameAnimation.start();
//            }
//        });
//    public void showDialog(View v) {
//        CustomDialogFragment dialog = new CustomDialogFragment();
//        dialog.show(getSupportFragmentManager(), "custom");
//    }



//    public void setDate(View v) {
//        new DatePickerDialog(MainActivity.this, d,
//                dateAndTime.get(Calendar.YEAR),
//                dateAndTime.get(Calendar.MONTH),
//                dateAndTime.get(Calendar.DAY_OF_MONTH))
//                .show();
//    }
//    public void setTime(View v) {
//        new TimePickerDialog(MainActivity.this, t,
//                dateAndTime.get(Calendar.HOUR_OF_DAY),
//                dateAndTime.get(Calendar.MINUTE), true)
//                .show();
//    }
//    private void setInitialDateTime() {
//        currentDateTime.setText(DateUtils.formatDateTime(this,
//                dateAndTime.getTimeInMillis(),
//                DateUtils.FORMAT_SHOW_DATE |
//                        DateUtils.FORMAT_SHOW_YEAR
//                        | DateUtils.FORMAT_SHOW_TIME));
//    }
//    TimePickerDialog.OnTimeSetListener t = (view, hourOfDay, minute) -> {
//        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
//        dateAndTime.set(Calendar.MINUTE, minute);
//        setInitialDateTime();
//    };
//    DatePickerDialog.OnDateSetListener d = (view, year, monthOfYear, dayOfMonth) -> {
//        dateAndTime.set(Calendar.YEAR, year);
//        dateAndTime.set(Calendar.MONTH, monthOfYear);
//        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        setInitialDateTime();
//    };

//    public void click(View v) {
//        Intent i=new Intent(this, MediaService.class);
//        if (v.getId()==R.id.start) {
//            startService(i);
//        }
//        else {
//            stopService(i);
//        }
//    }



//        videoPlayer = findViewById(R.id.videoPlayer);
//        videoPlayer.setVideoPath("https://www.youtube.com/watch?v=XYketIpTM-c");
////        Uri myVideoUri= Uri.parse( "https://www.youtube.com/watch?v=XYketIpTM-c");
////        videoPlayer.setVideoURI(myVideoUri);
//        MediaController mediaController = new MediaController(this);
//        videoPlayer.setMediaController(mediaController);
//        mediaController.setMediaPlayer(videoPlayer);


//    public void play(View view){
//        videoPlayer.start();
//    }
//    public void pause(View view){
//        videoPlayer.pause();
//    }
//    public void stop(View view){
//        videoPlayer.stopPlayback();
//        videoPlayer.resume();
//    }

