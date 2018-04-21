package com.example.vitto.uniapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav= findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenitore,new MainFragment()).commit(); //carico da subito il frammento main
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment =null;
            switch (item.getItemId()){
                case R.id.navMain:
                    selectedFragment = new MainFragment();
                    break;
                case R.id.navElenco:
                    selectedFragment= new ElencoFragment();
                    break;
                case R.id.navAggiungi:
                    selectedFragment= new AggiungiFragment();
                    break;
                case R.id.navPreferiti:
                    selectedFragment=new PreferitiFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContenitore,selectedFragment).commit();
            return true;
        }
    };
}
