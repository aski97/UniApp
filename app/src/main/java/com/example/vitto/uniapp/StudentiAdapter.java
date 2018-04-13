package com.example.vitto.uniapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vitto.uniapp.datamodel.Studente;

import java.util.List;

public class StudentiAdapter extends BaseAdapter {

    private Context context;
    private List<Studente> lista;

    public StudentiAdapter(Context context, List<Studente> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() { //restituisce numero elementi della lista
        return lista.size();
    }

    @Override
    public Studente getItem(int position) { //restituisce lo studente ad una certa posizione
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //associa ad ogni riga il layout creato
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.riga_studente_layout,null);
        }
        TextView vMatricola= convertView.findViewById(R.id.txtMatricolaS);
        TextView vNomeCompleto= convertView.findViewById(R.id.txtNomeCompleto);

        Studente s= lista.get(position);
        vMatricola.setText(s.getMatricola());
        vNomeCompleto.setText(s.getCognome()+" " + s.getNome());

        return convertView;
    }
}
