package com.example.prac11;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isStarted = new
            MutableLiveData<>(false);
    private MutableLiveData<Integer> value;
    public LiveData<Integer> getValue() {
        if (value == null) {
            value = new MutableLiveData<>(0);
        }
        return value;
    }
    public void execute(){
        if(Boolean.FALSE.equals(isStarted.getValue())){
            isStarted.postValue(true);
            Runnable runnable = () -> {
                for(int i = value.getValue(); i <= 100; i++){
                    try {
                        value.postValue(i);
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
