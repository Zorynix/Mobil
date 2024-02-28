package com.example.prac8;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    interface OnFragmentSendDataListener {
        void onSendData(String data);
    }
    private OnFragmentSendDataListener fragmentSendDataListener;
    String[] countries = {"Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView countriesList = view.findViewById(R.id.countriesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, countries);
        countriesList.setAdapter(adapter);
        countriesList.setOnItemClickListener((parent, v, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            fragmentSendDataListener.onSendData(selectedItem);
        });
        return view;
    }
}