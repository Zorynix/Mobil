package com.example.prac10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "Создать");
        SubMenu createSubMenu = menu.addSubMenu(0, 0, 0, "Создать документ");
        createSubMenu.add(0, 2, 0, "Текстовый документ");
        createSubMenu.add(0, 3, 0, "Графический документ");
        menu.add(0, 4, 1, "Открыть");
        menu.add(0, 5, 2, "Сохранить");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView headerView = findViewById(R.id.selectedMenuItem);
        switch(id){
            case 1 :
                headerView.setText("Создать документ");
                return true;
            case 2:
                headerView.setText("Открыть документ");
                return true;
            case 3:
                headerView.setText("Сохранить документ");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //headerView.setText(item.getTitle());
}