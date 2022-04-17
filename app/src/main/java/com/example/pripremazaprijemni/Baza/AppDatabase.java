package com.example.pripremazaprijemni.Baza;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


@Database(entities = {Pitanje.class}, version  = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PitanjeDao pitanjeDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Pitanja")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}

