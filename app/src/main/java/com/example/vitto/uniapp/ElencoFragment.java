package com.example.vitto.uniapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vitto.uniapp.datamodel.DataSource;
import com.example.vitto.uniapp.datamodel.Studente;

import java.util.List;

public class ElencoFragment extends Fragment {

    private ListView vListaStudenti;
    private DataSource dataSource;
    private StudentiAdapter adapter;
    private EditText edtRicerca;
    private Button btnRicerca;
    private  String prefisso;
    private TextView txtFail;
    private int numStudenti;
    private List<Studente> lista=null;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_elenco_studenti,container,false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vListaStudenti=view.findViewById(R.id.listStudenti);
        edtRicerca=view.findViewById(R.id.editRicerca);
        btnRicerca= view.findViewById(R.id.btnRicerca);
        txtFail= view.findViewById(R.id.txtFail);

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

        adapter = new StudentiAdapter(getContext(),lista); //passo l'elenco e il contesto in cui mi trovo all'adattatore

        vListaStudenti.setAdapter(adapter); //l'adattatore setta per ogni item la propria view

        return lista.size();
    }


}
