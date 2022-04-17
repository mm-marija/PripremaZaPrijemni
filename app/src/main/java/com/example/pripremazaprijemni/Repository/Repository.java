package com.example.pripremazaprijemni.Repository;


import com.example.pripremazaprijemni.Baza.AppDatabase;
import com.example.pripremazaprijemni.Baza.Pitanje;
import com.example.pripremazaprijemni.Baza.PitanjeDao;

import java.util.List;

import static com.example.pripremazaprijemni.MainActivity.mContext;

public class Repository {
    private static Repository repository;
    private PitanjeDao pitanjeDao;

    public static Repository getInstance(){
        if(repository == null){
            repository = new Repository();
        }
        return repository;
    }

    public Repository(){
        pitanjeDao = AppDatabase.getInstance(mContext).pitanjeDao();
    }

    public List<Pitanje> getSvaPitanja(){
        List<Pitanje> listaPitanja = pitanjeDao.svaPitanja();
        return listaPitanja;
    }

    public List<Pitanje> getRandomPitanja(){
        List<Pitanje> listaRandomPitanja = pitanjeDao.randomPitanja();
        return listaRandomPitanja;
    }
}

