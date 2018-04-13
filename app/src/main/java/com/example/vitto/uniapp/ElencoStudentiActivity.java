package com.example.vitto.uniapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ElencoStudentiActivity extends AppCompatActivity {

    private ListView vListaStudenti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elenco_studenti);

        vListaStudenti=findViewById(R.id.listStudenti);
    }
}
