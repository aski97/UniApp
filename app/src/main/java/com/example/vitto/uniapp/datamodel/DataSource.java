package com.example.vitto.uniapp.datamodel;
//Modella la fonte dei dati

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSource {
    //Singleton
    //Unica istanza dell'oggetto
    private static DataSource istanza =null;

    private DataSource() {
        popolaDataSource();
    }

    public static DataSource getIstanza(){
        if(istanza==null){
            istanza=new DataSource();
        }
        return  istanza;
    }

    //fine singleton
    //come utilizzerei una classe standard
    // DataSource dS= new DataSource();

    //come utilizzo un singleton
    //DataSource dS= DataSource().getIstanza();

    // Fonte dati per emulatore

    private HashMap<String,Studente> elenco;

    public  void aggiungiStudente(Studente studente){
        elenco.put(studente.getMatricola(),studente);
    }
    public  void eliminaStudente(String matricola){
        elenco.remove(matricola);
    }

    public  void aggiornaStudente(Studente studente){
        elenco.remove(studente.getMatricola());
        elenco.put(studente.getMatricola(),studente);
        //elenco.replace(studente.getMatricola(),studente);
    }

    public  Studente leggiStudente(String matricola){ //cerca uno studente per la matricola
        return elenco.get(matricola);
    }

    public List<Studente> elencoStudenti(String prefissoMatricola){

        ArrayList<Studente> risultato= new ArrayList<>();

        for (Map.Entry<String,Studente> elemento: elenco.entrySet()){
            if (elemento.getKey().startsWith(prefissoMatricola)) {
                risultato.add(elemento.getValue());
            }
        }

        return risultato;
    }

    private void popolaDataSource(){//istanziamo subito degli studenti in modo da provare l'app.
        elenco.put("A13001257",new Studente("A13001257","Belardo","Vittorio",180,26.5));
        elenco.put("A13001252",new Studente("A13001252","Purgato","Giovanna",180,26.5));
        elenco.put("A13001255",new Studente("A13001255","Criscuolo","Salvatore",180,26.5));
    }
}
