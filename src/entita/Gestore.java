package entita;

import java.time.LocalDate;
import java.util.*;
import eccezioni.LocaleGiaPresenteException;
import gestioneFile.FileGestoreRistorante;
import gestioneFile.FileRistorante;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author armuh
 */
public class Gestore extends Utente {

    List<Ristorante> ristoranti = new ArrayList<>();

    public Gestore(String nome, String cognome, String username, String password, LocalDate data, String luogodomicilio, String ruolo) {
        super(nome, cognome, username, password, data, luogodomicilio, ruolo);
        List<List<String>> gestoriRistoranti = new ArrayList<>();
        try {
            gestoriRistoranti = FileGestoreRistorante.letturaCsv(FileGestoreRistorante.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gestore.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean trovato = false;
        String ristoranti = "";
        for (int i = 0; i < gestoriRistoranti.size() || !trovato; i++) {
            if (gestoriRistoranti.get(i).get(0).compareTo(this.getUsername()) == 0) {
                trovato = true;
                ristoranti = gestoriRistoranti.get(i).get(1);
            }
        }
        String[] ristorantiSplittati = ristoranti.split("\\$");
        List<List<String>> ristorantiLetti = new ArrayList<>();
        try {
            //leggi file ristoranti
            ristorantiLetti = FileRistorante.letturaCsv(FileRistorante.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gestore.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (List<String> ristoranteLetto : ristorantiLetti) {
            for (int i = 0; i < ristorantiSplittati.length; i++) {
                if (ristoranteLetto.get(0).compareTo(ristorantiSplittati[i]) == 0) {
                    this.aggiungiRistorante(new Ristorante(ristoranteLetto.get(0), ristoranteLetto.get(1), 
                    ristoranteLetto.get(2), Float.parseFloat(ristoranteLetto.get(3)),
                    ristoranteLetto.get(4), Float.parseFloat(ristoranteLetto.get(5)),
                    Float.parseFloat(ristoranteLetto.get(6)), ristoranteLetto.get(7),
                    Boolean.parseBoolean(ristoranteLetto.get(8))/*rivedere*/, ristoranteLetto.get(9),
                    ristoranteLetto.get(10), Boolean.parseBoolean(ristoranteLetto.get(11))/*rivedere*/,
                    ristoranteLetto.get(12)));
                }
            }

        }
//                HashMap<Integer, Ristorante> ristoranti4 = new HashMap<>();
//                ristoranti4.
    }

    public void aggiungiRistorante(Ristorante r) {
        if (!ristoranti.contains(r)) {
            ristoranti.add(r);
        } else {
            throw new LocaleGiaPresenteException("Locale " + r.getNome() + " già presente");
        }
    }
}
