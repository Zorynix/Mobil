package com.example.prac9;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prac9.databinding.PracticeActivityBinding;

import java.util.ArrayList;


public class PracticeActivity extends AppCompatActivity {

    private PracticeActivityBinding binding;

    ArrayList<State> states = new ArrayList<State>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = PracticeActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();


//        binding.toggleButton.setOnClickListener(v -> {
//
//            Intent intent = new Intent(PracticeActivity.this, TestActivity.class);
//            startActivity(intent);
//        });
//
//        binding.toggleButton2.setOnClickListener(v -> {
//
//            Intent intent = new Intent(PracticeActivity.this, MainActivity.class);
//            startActivity(intent);
//        });
        setContentView(view);
        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.list);
        StateAdapter.OnStateClickListener stateClickListener = (state, position) -> Toast.makeText(getApplicationContext(), "Был выбран пункт " +
                        state.getName(),
                Toast.LENGTH_SHORT).show();
        StateAdapter adapter = new StateAdapter(this, states, stateClickListener);
        recyclerView.setAdapter(adapter);
    }
    private void setInitialData(){
        states.add(new State ("Бразилия", "Бразилиа", R.drawable.brazilia));
        states.add(new State ("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
        states.add(new State ("Колумбия", "Богота", R.drawable.columbia));
        states.add(new State ("Уругвай", "Монтевидео", R.drawable.uruguai));
        states.add(new State ("Чили", "Сантьяго", R.drawable.chile));
    }

}
//        String[] countries = { "Бразилия", "Аргентина", "Чили", "Колумбия", "Уругвай"};
//        GridView countriesList = findViewById(R.id.gridview);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, countries);
//        countriesList.setAdapter(adapter);
//        AdapterView.OnItemClickListener itemListener = (parent, view1, position, id) -> Toast.makeText(getApplicationContext(),"Вы выбрали "
//                        + parent.getItemAtPosition(position).toString(),
//                Toast.LENGTH_SHORT).show();
//        countriesList.setOnItemClickListener(itemListener);
//        String[] cities = {"Москва", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж"};
//        MultiAutoCompleteTextView autoCompleteTextView = findViewById(R.id.autocomplete);
//        ArrayAdapter<String> adapter = new ArrayAdapter(this,
//                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, cities);
//        autoCompleteTextView.setAdapter(adapter);
//        autoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
//        AutoCompleteTextView autoCompleteTextView =
//                findViewById(R.id.autocomplete);
//        ArrayAdapter<String> adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cities);
//        autoCompleteTextView.setAdapter(adapter);

//        String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
//        TextView selection = findViewById(R.id.selection);
//        Spinner spinner = findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        AdapterView.OnItemSelectedListener itemSelectedListener = new
//                AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int
//                            position, long id) {
//                        String item = (String)parent.getItemAtPosition(position);
//                        selection.setText(item);
//                    }
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//                    }
//                };
//        spinner.setOnItemSelectedListener(itemSelectedListener);
//    String[] countries = {"Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
//        Spinner spinner = findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, countries);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        ArrayList<Product> products = new ArrayList<Product>();
//        products.size();
//        products.add(new Product("Кириешки", "шт."));
//        products.add(new Product("Пиво", "шт."));
//        products.add(new Product("Сигареты", "шт."));
//        products.add(new Product("Энергетик", "шт."));
//        ListView productList = findViewById(R.id.productList);
//        ProductAdapter adapter = new ProductAdapter(this, R.layout.list_item,
//                products);
//        productList.setAdapter(adapter);
//        String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
//        ListView countriesList = findViewById(R.id.countriesList);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, countries);
//        countriesList.setAdapter(adapter);
//
//        ListView countriesList = findViewById(R.id.countriesList);
//        String[] countries = getResources().getStringArray(R.array.countries);
//        TextView selection = findViewById(R.id.selection);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, countries);
//        countriesList.setAdapter(adapter);
//        countriesList.setOnItemClickListener((parent, v, position, id) -> {
//            String selectedItem = countries[position];
//            selection.setText(selectedItem);
//        });
//    }
//        TextView selection = findViewById(R.id.selection);
//        ListView countriesList = findViewById(R.id.countriesList);
//        ArrayAdapter<String> adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_multiple_choice, countries);
//        countriesList.setAdapter(adapter);
//        countriesList.setOnItemClickListener((parent, v, position, id) -> {
//            SparseBooleanArray selected = countriesList.getCheckedItemPositions();
//            StringBuilder selectedItems = new StringBuilder();
//            for (int i = 0; i < countries.length; i++) {
//                if (selected.get(i))
//                    selectedItems.append(countries[i]).append(",");
//            }
//            // установка текста элемента TextView
//            selection.setText("Выбрано: " + selectedItems);
//        });
//    }
//        Collections.addAll(users, "Tom", "Bob", "Sam", "Alice");
//        usersList = findViewById(R.id.usersList);
//        adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_multiple_choice, users);
//        usersList.setAdapter(adapter);
//        usersList.setOnItemClickListener((parent, v, position, id) -> {
//            String user = adapter.getItem(position);
//            if(usersList.isItemChecked(position))
//                selectedUsers.add(user);
//            else
//                selectedUsers.remove(user);
//        });
//    }
//    public void add(View view){
//        EditText userName = findViewById(R.id.userName);
//        String user = userName.getText().toString();
//        if(!user.isEmpty()){
//            adapter.add(user);
//            userName.setText("");
//            adapter.notifyDataSetChanged();
//        }
//    }
//    public void remove(View view){
//        for(int i=0; i< selectedUsers.size();i++){
//            adapter.remove(selectedUsers.get(i));
//        }
//        usersList.clearChoices();
//        selectedUsers.clear();
//        adapter.notifyDataSetChanged();
//    }
//        setInitialData();
//        countriesList = findViewById(R.id.countriesList);
//        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item,
//                states);
//        countriesList.setAdapter(stateAdapter);
//        AdapterView.OnItemClickListener itemListener = (parent, v, position, id) -> {
//            State selectedState = (State) parent.getItemAtPosition(position);
//            Toast.makeText(getApplicationContext(), "Был выбран пункт " +
//                            selectedState.getName(),
//                    Toast.LENGTH_SHORT).show();
//        };
//        countriesList.setOnItemClickListener(itemListener);
//    }
//
//    private void setInitialData() {
//        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazilia));
//        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
//        states.add(new State("Колумбия", "Богота", R.drawable.columbia));
//        states.add(new State("Уругвай", "Монтевидео", R.drawable.uruguai));
//        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
//        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazilia));
//        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
//        states.add(new State("Колумбия", "Богота", R.drawable.columbia));
//        states.add(new State("Уругвай", "Монтевидео", R.drawable.uruguai));
//        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
//        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazilia));
//        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
//        states.add(new State("Колумбия", "Богота", R.drawable.columbia));
//        states.add(new State("Уругвай", "Монтевидео", R.drawable.uruguai));
//        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
//        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazilia));
//        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
//        states.add(new State("Колумбия", "Богота", R.drawable.columbia));
//        states.add(new State("Уругвай", "Монтевидео", R.drawable.uruguai));
//        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
//        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazilia));
//        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
//        states.add(new State("Колумбия", "Богота", R.drawable.columbia));
//        states.add(new State("Уругвай", "Монтевидео", R.drawable.uruguai));
//        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
//    }