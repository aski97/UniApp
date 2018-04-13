package com.example.vitto.uniapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vitto.uniapp.datamodel.Studente;

public class DettaglioStudenteActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView txtCognome;
    private TextView txtMatricola;
    private TextView txtCFU;
    private TextView txtMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_studente);

        Intent intent = getIntent();
        Studente studente =(Studente) intent.getSerializableExtra("STUDENTE");

        txtNome= findViewById(R.id.txtNomeS);
        txtCognome= findViewById(R.id.txtCognomeS);
        txtMatricola= findViewById(R.id.txtMatricolaS);
        txtCFU= findViewById(R.id.txtCfuS);
        txtMedia= findViewById(R.id.txtMediaS);

        txtNome.setText(studente.getNome());
        txtCognome.setText(studente.getCognome());
        txtMatricola.setText(studente.getMatricola());
        txtCFU.setText(String.valueOf(studente.getCfu()));
        txtMedia.setText(String.valueOf(studente.getMedia()));

    }
}
