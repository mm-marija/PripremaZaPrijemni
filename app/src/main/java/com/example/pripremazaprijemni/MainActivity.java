package com.example.pripremazaprijemni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pripremazaprijemni.Baza.AppDatabase;
import com.example.pripremazaprijemni.Baza.Pitanje;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    Button informacije, informator, prirucnik, test;
    TextView naslov;
    DownloadManager downloadManager;
    long downLoadId;
    public static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        informacije = findViewById(R.id.informacije);
        informator = findViewById(R.id.informator);
        prirucnik = findViewById(R.id.prirucnik);
        test = findViewById(R.id.test);
        naslov = findViewById(R.id.naslov);
        mContext = getApplicationContext();

        informacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Informacije.class));
            }
        });

        informator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nazivFajla = "Информатор";
                Uri fajl = Uri.parse("https://ar.asss.edu.rs/Informator-akademija-2020_korekcija.pdf");
                preuzmiFajl(fajl, nazivFajla);
            }
        });

        prirucnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nazivFajla = "Приручник";
                Uri fajl = Uri.parse("https://vsar.edu.rs/wp-content/uploads/2021/01/PRIRUcNIK-2021.pdf");
                preuzmiFajl(fajl, nazivFajla);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Test.class);
                startActivity(intent);
            }
        });
    }

    private void preuzmiFajl(Uri uri, String nazivFajla) {
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(nazivFajla)
                .setDescription(nazivFajla)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        downLoadId = downloadManager.enqueue(request);
    }
}
