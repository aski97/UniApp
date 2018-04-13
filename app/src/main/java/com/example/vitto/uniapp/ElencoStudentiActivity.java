package com.example.vitto.uniapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vitto.uniapp.datamodel.DataSource;
import com.example.vitto.uniapp.datamodel.Studente;

import java.util.List;

public class ElencoStudentiActivity extends AppCompatActivity {

    private ListView vListaStudenti;
    private DataSource dataSource;
    private StudentiAdapter adapter;
    private EditText edtRicerca;
    private Button btnRicerca;
    private  String prefisso;
    private TextView txtFail;
    private int numStudenti;
    private List<Studente> lista=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elenco_studenti);

        vListaStudenti=findViewById(R.id.listStudenti);
        edtRicerca=findViewById(R.id.editRicerca);
        btnRicerca= findViewById(R.id.btnRicerca);
        txtFail= findViewById(R.id.txtFail);

        dataSource= DataSource.getIstanza(); //istanza del singleton DataSource


        numStudenti = queryRicerca(""); //la prima query mostrerà tutti gli Studenti

       vListaStudenti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Studente s= lista.get(position);
                //intent esplicito avvia l'activty
                Intent intent = new Intent(view.getContext(),DettaglioStudenteActivity.class);
                intent.putExtra("STUDENTE",s); //gli passo la classe studente serializzata
                startActivity(intent);
            }
        });

       btnRicerca.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               txtFail.setVisibility(View.INVISIBLE);
               prefisso = edtRicerca.getText().toString();
               numStudenti = queryRicerca(prefisso);
               if (numStudenti == 0) {
                   txtFail.setVisibility(View.VISIBLE);
                   txtFail.setText(R.string.fail);
               }
           }
       });
    }

    private int queryRicerca(String prefisso){ //mi restituisce il numero di Studenti trovati

        lista= dataSource.elencoStudenti(prefisso); //inizio facendo una query e aggiungo il risultato nella lista

        adapter = new StudentiAdapter(getApplicationContext(),lista); //passo l'elenco e il contesto in cui mi trovo all'adattatore

        vListaStudenti.setAdapter(adapter); //l'adattatore setta per ogni item la propria view

        return lista.size();
    }

}
