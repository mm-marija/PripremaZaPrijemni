package com.example.pripremazaprijemni.ViewModel;

import androidx.lifecycle.ViewModel;
import com.example.pripremazaprijemni.Baza.Pitanje;
import com.example.pripremazaprijemni.Repository.Repository;
import java.util.List;

public class TestViewModel extends ViewModel {
    private List<Pitanje> pitanja;
    private List<Pitanje> randomPitanja;
    private Repository repository;

    public void init(){
        if(pitanja != null){
            return;
        }
        if(randomPitanja != null){
            return;
        }
        repository = Repository.getInstance();
    }

    public List<Pitanje> svaPitanja(){
        pitanja = repository.getSvaPitanja();
        return pitanja;
    }
    public List<Pitanje> randomPitanja(){
        randomPitanja = repository.getRandomPitanja();
        return randomPitanja;
    }
}

