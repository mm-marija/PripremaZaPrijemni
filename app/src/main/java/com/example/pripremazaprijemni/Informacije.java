package com.example.pripremazaprijemni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Informacije extends AppCompatActivity {
    ImageButton prijava, prijemni, rangiranje;
    FloatingActionButton nazad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacije);
        getSupportActionBar().hide();
        prijava = findViewById(R.id.prijavaBtn);
        prijemni = findViewById(R.id.prijemniBtn);
        rangiranje = findViewById(R.id.rangiranjeBtn);
        nazad = findViewById(R.id.floatingActionButton);

        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Informacije.this, MainActivity.class));
            }
        });

       prijava.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Dialog dialog1 = new Dialog(Informacije.this);
               dialog1.setContentView(R.layout.prijavljivanje);
               dialog1.setTitle("Пријављивање");

               Button  closePrijavljivanje = (Button) dialog1.findViewById(R.id.closePrijavljivanje);
               closePrijavljivanje.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog1.dismiss();
                   }
               });
               dialog1.show();
           }
       });

        prijemni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog2 = new Dialog(Informacije.this);
                dialog2.setContentView(R.layout.prijemni_ispit);
                dialog2.setTitle("Пријемни испит");

                Button  closePrijemniIspit = (Button) dialog2.findViewById(R.id.closePrjemniIspit);
                closePrijemniIspit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                dialog2.show();
            }
        });

        rangiranje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog3 = new Dialog(Informacije.this);
                dialog3.setContentView(R.layout.rangiranje_i_upis);
                dialog3.setTitle("Рангирање и упис");

                Button  closeRangiranjeIUpis = (Button) dialog3.findViewById(R.id.closeRangiranjeIUpis);
                closeRangiranjeIUpis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });
                dialog3.show();
            }
        });

    }
}