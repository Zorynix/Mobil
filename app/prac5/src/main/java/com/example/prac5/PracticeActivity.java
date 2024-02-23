package com.example.prac5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.prac5.databinding.PracticeActivityBinding;
import com.google.android.material.snackbar.Snackbar;


public class PracticeActivity extends AppCompatActivity {

    private PracticeActivityBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = PracticeActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


        binding.toggleButton.setOnClickListener(v -> {

            Intent intent = new Intent(PracticeActivity.this, TestActivity.class);
            startActivity(intent);
        });

        binding.toggleButton2.setOnClickListener(v -> {

            Intent intent = new Intent(PracticeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        setContentView(view);

        TextView selection = findViewById(R.id.selection);
        CheckBox enableBox = findViewById(R.id.enabled);
        enableBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                selection.setText("Включено");
                buttonView.setText("Выключить");
            }
            else {
                selection.setText("Выключено");
                buttonView.setText("Включить");
            }
        });
    }
}




//    public void onCheckboxClicked(View view) {
//        CheckBox checkBox = (CheckBox) view;
//        boolean checked = checkBox.isChecked();
//        TextView selection = findViewById(R.id.selection);
//        if (view.getId() == R.id.java)
//            if (checked)
//                Toast.makeText(this, "Вы выбрали Java ",Toast.LENGTH_LONG).show();
//        else if (view.getId() ==R.id.kotlin)
//            if (checked)
//                Toast.makeText(this, "Вы выбрали Kotlin",Toast.LENGTH_LONG).show();
//        }


//        TextView timeTextView = binding.timeTextView;
//        TimePicker timePicker = binding.timePicker;
//        timePicker.setOnTimeChangedListener((view1, hourOfDay, minute) ->
//                timeTextView.setText("Время: " + hourOfDay + ":" + minute));

//        ConstraintLayout layout = new ConstraintLayout(this);
//        ConstraintLayout.LayoutParams layoutParams = new
//                ConstraintLayout.LayoutParams
//                (ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                        ConstraintLayout.LayoutParams.WRAP_CONTENT);
//        ToggleButton toggleButton = new ToggleButton(this);
//        toggleButton.setTextOff("Выключено");
//        toggleButton.setTextOn("Включено");
//        toggleButton.setText("Выключено");
//        toggleButton.setOnClickListener(view1 -> {
//            boolean on = ((ToggleButton) view1).isChecked();
//            if (on) {
//                Toast.makeText(getApplicationContext(), "Свет включен",
//                        Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Свет выключен!",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
//        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
//        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
//        layout.addView(toggleButton);

//        SeekBar seekBar = binding.seekBar;
//        TextView textView = binding.seekBarValue;
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                textView.setText(String.valueOf(progress));
//            }
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });

//    public void onToggleClicked(View view) {
//        boolean on = ((ToggleButton) view).isChecked();
//        if (on) {
//            Toast.makeText(this, "Свет включен", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "Свет выключен!",
//                    Toast.LENGTH_LONG).show();
//        }


//         TextView dateTextView = binding.dateTextView;
//         DatePicker datePicker = binding.datePicker;
//         datePicker.init(2024, 02, 23,
//                 (view1, year, monthOfYear, dayOfMonth) -> dateTextView.setText("Дата: " + view1.getDayOfMonth() + "/" +
//                 (view1.getMonth() + 1) + "/" + view1.getYear()));

//        ConstraintLayout constraintLayout = new ConstraintLayout(this);
//        TextView textView = new TextView(this);
//        textView.setAllCaps(false);
//        textView.setText("https://vk.com/im");
//        textView.setTypeface(Typeface.create("casual", Typeface.BOLD));
//        textView.setTextSize(20);
//        textView.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        textView.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
//        Linkify.addLinks(textView, Linkify.WEB_URLS);
//        TextView textView2 = new TextView(this);
//        textView2.setAllCaps(true);
//        textView2.setText("+78005553535");
//        textView2.setPadding(0,200,0,0);
//        textView2.setTypeface(Typeface.create("casual", Typeface.BOLD));
//        textView2.setTextSize(20);
//        Linkify.addLinks(textView2, Linkify.PHONE_NUMBERS);
//        textView.setLinksClickable(true);
//        ConstraintLayout.LayoutParams layoutParams = new
//                ConstraintLayout.LayoutParams
//                (ConstraintLayout.LayoutParams.MATCH_PARENT,
//                        ConstraintLayout.LayoutParams.MATCH_PARENT);
//        textView.setLayoutParams(layoutParams);
//        textView2.setLayoutParams(layoutParams);
//        constraintLayout.addView(textView);
//        constraintLayout.addView(textView2);
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );


//        binding.editText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                binding.textView.setText("Привет, " + s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//
//        });
        //EditText editText2 = new EditText(this);

//        ConstraintLayout constraintLayout = new ConstraintLayout(this);
//
//        TextView textView = new TextView(this);
//        EditText editText = new EditText(this);
//        Button button = new Button(this);
//        button.setText("Ввод");
//
//        editText.setHint("Введите ваше имя");
//        editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
//
//        ConstraintLayout.LayoutParams buttonLayout = new
//               ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//               ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        buttonLayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
//        buttonLayout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
//        buttonLayout.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
//        buttonLayout.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
//        button.setLayoutParams(buttonLayout);
//        constraintLayout.addView(button);
//
//        ConstraintLayout.LayoutParams editTextLayout = new
//                ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        editTextLayout.topToBottom = textView.getId();
//        editTextLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
//        editTextLayout.rightToRight =
//                ConstraintLayout.LayoutParams.PARENT_ID;
//        editText.setLayoutParams(editTextLayout);
//        constraintLayout.addView(editText);
//        ConstraintLayout.LayoutParams textViewLayout = new
//                ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
//                ConstraintLayout.LayoutParams.WRAP_CONTENT
//        );
//        textViewLayout.topToBottom = editText.getId();
//        textViewLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
//        textViewLayout.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
//        textViewLayout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
//        textViewLayout.bottomMargin = 1500;
//        textViewLayout.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
//        textViewLayout.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
//
//        textView.setLayoutParams(textViewLayout);
//        constraintLayout.addView(textView);


//        binding.button.setOnClickListener(v -> {
//            binding.textView.setText("Привет, " + binding.editText.getText());
//        });

//        binding.button.setOnClickListener(v -> {
//            Toast toast = Toast.makeText(this, "Практика 5", Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.TOP, 0,160); // import android.view.Gravity;
//            toast.show();
//        });

//        binding.button.setOnClickListener(v -> {
//            Snackbar snackbar = Snackbar.make(view, "Практика 5",
//                    Snackbar.LENGTH_LONG);
//            snackbar.setTextColor(0XFF81C784);
//            snackbar.setBackgroundTint(0XFF555555);
//            snackbar.setActionTextColor(0XFF0277BD);
//            snackbar.setAction("Далее...", v1 -> {
//                Toast toast = Toast.makeText(getApplicationContext(), "Далее clicked!",Toast.LENGTH_LONG);
//                toast.show();
//            });
//            snackbar.show();
//        })
    //}
//    public void onRadioButtonClicked(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//        TextView selection = binding.selection;
//        if (view.getId() == R.id.java) {
//            if (checked) {
//                selection.setText("Выбрана Java");
//            }
//        } else if (view.getId() == R.id.kotlin) {
//            if (checked) {
//                selection.setText("Выбран Kotlin");
//            }
//        }
//    }


        //editText2.setLayoutParams(layoutParams);
        //linearLayout.addView(editText2);


