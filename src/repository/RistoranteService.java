/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.AssGestoreRistoranti;
import entita.Gestore;
import entita.Ristorante;
import gestioneFile.FileRistorante;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import menu.RistoranteUI;

/**
 *
 * @author armuh
 */
public class RistoranteService {

    private FileRistorante fileRistorante = new FileRistorante();
    private HashMap<String, Ristorante> ristoranti = new FileRistorante().ottieniHashMap();
    private Scanner scanner = new Scanner(System.in);
//    private RistoranteUI ristoranteUI = new RistoranteUI(scanner);
    
    public HashMap<String, Ristorante> getRistoranti() {
        return ristoranti;
    }

    public void setRistoranti(HashMap<String, Ristorante> ristoranti) {
        this.ristoranti = ristoranti;
    }

    public boolean containsKey(String chiave) {
        return ristoranti.containsKey(chiave);
    }

    public Ristorante get(String chiave) {
        if (ristoranti.containsKey(chiave)) {
            return ristoranti.get(chiave);
        } else {
            return null;
        }
    }
    
    public Collection<Ristorante> values(){
        return  ristoranti.values();
    }
    
    
    
    public boolean ristoranteGiaPossedutoDalGestore(Gestore utente, Ristorante ristorante) {
        HashMap<String, AssGestoreRistoranti> mappa = AssGestoreRistoranti.getRistorantiMap();
        AssGestoreRistoranti gestore = mappa.get(utente.getUsername());
        return gestore != null && gestore.ristoranteInPossessoDaUtente(ristorante);
    }

    public boolean ristoranteHaAltroProprietario(Gestore utente, Ristorante ristorante) {
        return AssGestoreRistoranti.ristoranteInPossessoDaUtenti(ristorante);
    }
}
