package com.example.pripremazaprijemni;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pripremazaprijemni.Baza.Pitanje;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context, dContext;
    private List<Pitanje> listaPitanja;
    public String pit, odgA, odgB, odgV, odgG;
    static int i = 0;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void postaviListuPitanja(List<Pitanje> listaPitanja) {
        this.listaPitanja = listaPitanja;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView pitanje;
        public RadioButton rbOdgovorA, rbOdgovorB, rbOdgovorV, rbOdgovorG;
        public RadioGroup odgovori;
        public TextView redniBroj;

        public MyViewHolder(@NonNull View view) {
            super(view);
            odgovori = view.findViewById(R.id.odgovori);
            pitanje = view.findViewById(R.id.tvPitanje);
            rbOdgovorA = view.findViewById(R.id.rbOdgovorA);
            rbOdgovorB = view.findViewById(R.id.rbOdgovorB);
            rbOdgovorV = view.findViewById(R.id.rbOdgovorV);
            rbOdgovorG = view.findViewById(R.id.rbOdgovorG);
            redniBroj = view.findViewById(R.id.redniBroj);
        }
    }

    public class MyViewHolderPoslednji extends RecyclerView.ViewHolder{
        Button potvrdi;
        public MyViewHolderPoslednji(@NonNull View view) {
            super(view);
            potvrdi = view.findViewById(R.id.potvrdi);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        dContext = parent.getContext();
        if(viewType == getItemCount()-1){
            view = LayoutInflater.from(context).inflate(R.layout.poslednji_red, parent, false);
            return new MyViewHolderPoslednji(view);
        }
        else{
            view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(position != getItemCount()-1) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            pit = this.listaPitanja.get(position).pitanje;
            odgA = this.listaPitanja.get(position).odgovorA.trim();
            odgB = this.listaPitanja.get(position).odgovorB.trim();
            odgV = this.listaPitanja.get(position).odgovorV.trim();
            odgG = this.listaPitanja.get(position).odgovorG.trim();

            if (odgA.contains("tacno")) {
                odgA = odgA.replace("tacno", "\t");
            }
            if (odgB.contains("tacno")) {
                odgB = odgB.replace("tacno", "\t");
            }
            if (odgV.contains("tacno")) {
                odgV = odgV.replace("tacno", "\t");
            }
            if (odgG.contains("tacno")) {
                odgG = odgG.replace("tacno", "\t");
            }

            myViewHolder.redniBroj.setText(String.valueOf(position + 1) + ".");
            myViewHolder.pitanje.setText(pit);
            myViewHolder.rbOdgovorA.setText(odgA);
            myViewHolder.rbOdgovorB.setText(odgB);
            myViewHolder.rbOdgovorV.setText(odgV);
            myViewHolder.rbOdgovorG.setText(odgG);
            myViewHolder.odgovori.clearCheck();

            myViewHolder.odgovori.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.rbOdgovorA:
                            if (myViewHolder.rbOdgovorA.getText().toString().contains("\t")) {
                                myViewHolder.rbOdgovorA.setTextColor(Color.GREEN);
                                i++;
                            }
                            provera(myViewHolder.rbOdgovorB, myViewHolder.rbOdgovorV, myViewHolder.rbOdgovorG);
                            myViewHolder.rbOdgovorA.setChecked(true);
                            disableClik(myViewHolder.odgovori);
                            break;
                        case R.id.rbOdgovorB:
                            if (myViewHolder.rbOdgovorB.getText().toString().contains("\t")) {
                                myViewHolder.rbOdgovorB.setTextColor(Color.GREEN);
                                i++;
                            }
                            provera(myViewHolder.rbOdgovorA, myViewHolder.rbOdgovorV, myViewHolder.rbOdgovorG);
                            myViewHolder.rbOdgovorB.setChecked(true);
                            disableClik(myViewHolder.odgovori);
                            break;
                        case R.id.rbOdgovorV:
                            if (myViewHolder.rbOdgovorV.getText().toString().contains("\t")) {
                                myViewHolder.rbOdgovorV.setTextColor(Color.GREEN);
                                i++;
                            }
                            provera(myViewHolder.rbOdgovorA, myViewHolder.rbOdgovorB, myViewHolder.rbOdgovorG);
                            myViewHolder.rbOdgovorV.setChecked(true);
                            disableClik(myViewHolder.odgovori);
                            break;
                        case R.id.rbOdgovorG:
                            if (myViewHolder.rbOdgovorG.getText().toString().contains("\t")) {
                                myViewHolder.rbOdgovorG.setTextColor(Color.GREEN);
                                i++;
                            }
                            provera(myViewHolder.rbOdgovorA, myViewHolder.rbOdgovorB, myViewHolder.rbOdgovorV);
                            myViewHolder.rbOdgovorG.setChecked(true);
                            disableClik(myViewHolder.odgovori);
                            break;
                    }
                }
            });
        }

        else{
            MyViewHolderPoslednji myViewHolderPoslednji = (MyViewHolderPoslednji)holder;
            myViewHolderPoslednji.potvrdi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(dContext);
                    dialog.setContentView(R.layout.rezultat);
                    dialog.setTitle("Rezultat");
                    TextView rezultatTekstNaslov = (TextView) dialog.findViewById(R.id.rezTv);
                    TextView rezulatTekst = (TextView) dialog.findViewById(R.id.rezTv2);

                    rezultatTekstNaslov.setText("Резултат је: " + String.valueOf(i) + " тачних одговора.");
                    if(i < 15){rezulatTekst.setText("Потребно је да се боље припремиш.");}
                    else if(i > 15 && i < 30){rezulatTekst.setText("Уради тест још неколико пута," + "\n" + "како би се још боље припремио/ла.");}
                    else if(i > 30 && i < 50){rezulatTekst.setText("Уради тест још неколико пута," + "\n" + "како би утврдио/ла своје знање.");}
                    else if(i > 50){rezulatTekst.setText("Браво! Спреман/на си за пријемни испит.");}

                    Button noviTest = (Button) dialog.findViewById(R.id.noviTest);

                    noviTest.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            i=0;
                            context.startActivity(new Intent(context, Test.class));
                            dialog.dismiss();
                        }
                    });

                    Button pocetna = (Button) dialog.findViewById(R.id.pocetna);

                    pocetna.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            i=0;
                            context.startActivity(new Intent(context, MainActivity.class));
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return  this.listaPitanja.size()+1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void disableClik(RadioGroup group){
        for(int i = 0; i < group.getChildCount(); i++){
            ((RadioButton)group.getChildAt(i)).setEnabled(false);}
    }

    public void provera(RadioButton rb1, RadioButton rb2, RadioButton rb3){
        if(rb1.getText().toString().contains("\t")){
            rb1.setTextColor(Color.GREEN);
        }
        else if(rb2.getText().toString().contains("\t")){
            rb2.setTextColor(Color.GREEN);
        }
        else if(rb3.getText().toString().contains("\t")){
            rb3.setTextColor(Color.GREEN);
        }
    }
}
