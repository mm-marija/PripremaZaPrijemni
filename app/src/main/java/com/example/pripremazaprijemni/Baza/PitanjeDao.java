package com.example.pripremazaprijemni.Baza;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PitanjeDao {
    @Query("SELECT * FROM Pitanje")
    List<Pitanje> svaPitanja();

    @Query("SELECT * FROM Pitanje ORDER BY RANDOM() LIMIT 60")
    List<Pitanje> randomPitanja();

    @Insert
    void unesiPitanje(Pitanje... pitanjes);
}

