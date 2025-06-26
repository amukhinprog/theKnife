/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import repository.generico.HashMapService;
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
public class RecensioneService extends HashMapService<Integer, Recensione> {

    private static final FileRecensioni FR = new FileRecensioni();

    private AssGestoreRistorantiService assGestoreRistorantiServ = new AssGestoreRistorantiService();
    private static int ID = 0;

    public float mediaStelle(Ristorante r) {
        int somma = 0;
        int ripetizioni = 0;
        String nome = r.getNome();
        for (Recensione recensione : map.values()) {
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
        if (map == null || map.isEmpty()) {
            return 0.0;  // nessuna recensione
        }

        double somma = 0.0;
        for (Recensione r : map.values()) {
            somma += r.getStelle();  // supponendo che getStelle() ritorni un int o double
        }

        return somma / map.size();
    }

    public HashMap<Ristorante, Float> mediaStelle(Gestore gestore) {
        HashMap<Ristorante, Float> mediaStelleMap = new HashMap<>();
        List<Ristorante> listaRistorantiposseduti = assGestoreRistorantiServ.get(gestore.getUsername()).getRistorantiList();
        int sommaTot = 0;
        int countTot = 0;
        float mediaStelle = 0;
        for (Ristorante ristoranteP : listaRistorantiposseduti) {
            int somma = 0;
            int count = 0;
            for (Recensione recensione : map.values()) {
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

    @Override
    protected Integer getKey(Recensione recensione) {
        return recensione.getID();
    }

    public static int getID() {
        return ID;
    }

    public static int incID() {
        return ++ID;
    }

    @Override
    protected HashMap<Integer, Recensione> lettura() {
        return FR.ottieniHashMap();
    }

    @Override
    protected void scrittura(Recensione valore) {
        FR.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<Integer, Recensione> map) {
        FR.sovraScrivi(map);
    }

}
