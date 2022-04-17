package com.example.pripremazaprijemni;

import android.app.Application;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.pripremazaprijemni.Baza.AppDatabase;
import com.example.pripremazaprijemni.Baza.Pitanje;
import com.example.pripremazaprijemni.ViewModel.TestViewModel;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.util.ArrayList;
import java.util.Scanner;

public class UnosPitanja extends Application {
    String extractedText = "";
    ArrayList<String> extractedPitanja = new ArrayList<>();
    ArrayList<String> odgovoriA = new ArrayList<>();
    ArrayList<String> odgovoriB = new ArrayList<>();
    ArrayList<String> odgovoriV = new ArrayList<>();
    ArrayList<String> odgovoriG = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        boolean mboolean = false;
        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) {
            extractPDF();
            unesiPitanja();
            settings = getSharedPreferences("PREFS_NAME", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("FIRST_RUN", true);
            editor.commit();}
        else {}
    }

    private void unesiPitanja() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        for (int i = 0; i < 351; i++) {
            Pitanje pitanjeUnos = new Pitanje(extractedPitanja.get(i),
                    odgovoriA.get(i), odgovoriB.get(i),
                    odgovoriV.get(i), odgovoriV.get(i));
            db.pitanjeDao().unesiPitanje(pitanjeUnos);
        }
    }

    public void extractPDF(){
        try {
            PdfReader reader = new PdfReader("res/raw/prirucnik.pdf");
            int n = reader.getNumberOfPages();
            for (int i = 0; i < n; i++) {
                extractedText = extractedText + PdfTextExtractor.getTextFromPage(reader,
                        i + 1).trim() + "\n";
            }
            Scanner scanner = new Scanner(extractedText);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (Character.isUpperCase(line.codePointAt(0))) {
                    extractedPitanja.add(line);
                }
                if (line.contains("а)")) {
                    odgovoriA.add(line);
                } else if (line.contains("б)")) {
                    odgovoriB.add(line);
                } else if (line.contains("в)")) {
                    odgovoriV.add(line);
                } else if (line.contains("г)")) {
                    odgovoriG.add(line);
                }
            }
            scanner.close();
            reader.close();
        } catch (Exception e) {
            Toast.makeText(this, "Greska: \n" + e, Toast.LENGTH_LONG).show();
        }
    }
}

