/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.Gestore;
import entita.Recensione;
import entita.Ristorante;
import entita.Utente;
import gestioneFile.FileRecensioni;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import menu.RecensioneUI;
import menu.RistoranteUI;

/**
 *
 * @author armuh
 */
public class RecensioneService extends AssGestoreRistorantiService {

    private HashMap<Integer, Recensione> recensioniHashMap = new FileRecensioni().ottieniHashMap();
    private FileRecensioni fileRecensioni = new FileRecensioni();
    private static int ID = 0;

    public HashMap<Integer, Recensione> getRecensioniHashMap() {
        return new FileRecensioni().ottieniHashMap();
    }

    public float mediaStelle(Ristorante r) {
        int somma = 0;
        int ripetizioni = 0;
        String nome = r.getNome();
        for (Recensione recensione : recensioniHashMap.values()) {
            if (recensione.getRistoranteRecensito().equals(nome)) {
                somma += recensione.getStelle();
                ripetizioni++;
            }
        }
        try {
            return (float) (somma / ripetizioni);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public double mediaStelle() {
        if (recensioniHashMap == null || recensioniHashMap.isEmpty()) {
            return 0.0;  // nessuna recensione
        }

        double somma = 0.0;
        for (Recensione r : recensioniHashMap.values()) {
            somma += r.getStelle();  // supponendo che getStelle() ritorni un int o double
        }

        return somma / recensioniHashMap.size();
    }

    public HashMap<Ristorante, Float> mediaStelle(Gestore gestore) {
        HashMap<Ristorante, Float> mediaStelleMap = new HashMap<>();
        List<Ristorante> listaRistorantiposseduti = ristorantiMap.get(gestore.getUsername()).getRistorantiList();
        int sommaTot = 0;
        int countTot = 0;
        float mediaStelle = 0;
        for (Ristorante ristoranteP : listaRistorantiposseduti) {
            int somma = 0;
            int count = 0;
            for (Recensione recensione : recensioniHashMap.values()) {
                if (recensione.getRistoranteRecensito().equals(ristoranteP)) {
                    somma += recensione.getStelle();
                    count++;
                }
            }
            mediaStelle = somma / count;
            mediaStelleMap.put(ristoranteP, mediaStelle);
            sommaTot += somma;
            countTot++;
        }
        //da completare
        return mediaStelleMap;
    }

    public void setRecensioniHashMap(HashMap<Integer, Recensione> recensioniHashMap) {
        this.recensioniHashMap = recensioniHashMap;
    }

    public void add(Recensione recensione) {
        recensione.setID(incID());
        fileRecensioni.scrittura(recensione);
    }

    public void put(Integer id, Recensione recensione) {
        recensioniHashMap.put(id, recensione);
        fileRecensioni.sovraScrivi(recensioniHashMap);
    }

    public void remove(Integer id) {
        recensioniHashMap.remove(id);
        fileRecensioni.sovraScrivi(recensioniHashMap);
    }

    public static int getID() {
        return ID;
    }

    public static int incID() {
        return ++ID;
    }
}
