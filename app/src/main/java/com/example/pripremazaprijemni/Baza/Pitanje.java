package com.example.pripremazaprijemni.Baza;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pitanje {
    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "pitanje")
    public String pitanje;

    @ColumnInfo(name = "odgovorA")
    public String odgovorA;

    @ColumnInfo(name = "odgovorB")
    public String odgovorB;

    @ColumnInfo(name = "odgovorV")
    public String odgovorV;

    @ColumnInfo(name = "odgovorG")
    public String odgovorG;

    public Pitanje(String pitanje, String odgovorA, String odgovorB, String odgovorV, String odgovorG) {
        this.pitanje = pitanje;
        this.odgovorA = odgovorA;
        this.odgovorB = odgovorB;
        this.odgovorV = odgovorV;
        this.odgovorG = odgovorG;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovorA() {
        return odgovorA;
    }

    public void setOdgovorA(String odgovorA) {
        this.odgovorA = odgovorA;
    }

    public String getOdgovorB() {
        return odgovorB;
    }

    public void setOdgovorB(String odgovorB) {
        this.odgovorB = odgovorB;
    }

    public String getOdgovorV() {
        return odgovorV;
    }

    public void setOdgovorV(String odgovorV) {
        this.odgovorV = odgovorV;
    }

    public String getOdgovorG() {
        return odgovorG;
    }

    public void setOdgovorG(String odgovorG) {
        this.odgovorG = odgovorG;
    }
}
