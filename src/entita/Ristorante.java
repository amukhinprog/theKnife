package entita;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
import java.io.*;
import gestioneFile.FileRistorante;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.ArrayList;

/**
 *
 * @author armuh
 */
public class Ristorante {

    public Ristorante() {
    }

    public static List<List<String>> cercaRistorante(int a) {
        switch (a) {
            case 1:
                return cercaRistorantePerLocazione();
        }
        return new ArrayList<>();
    }

    private static List<List<String>> cercaRistorantePerLocazione() {
        Scanner scanner = new Scanner(System.in);
        String locazione = scanner.next();
        List<List<String>> ristoranti = new ArrayList<>();
        int posizioneLocazioneInCsv = 2;
        List<List<String>> ristorantiCorrelati = new ArrayList<>();
        try {
            ristoranti = FileRistorante.letturaCsv();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        for (int i = 0; i < ristoranti.size(); i++) {
            System.out.println(ristoranti.get(i).get(posizioneLocazioneInCsv));
            String locazioneRistorante = ristoranti.get(i).get(posizioneLocazioneInCsv).replace("\"", "");
            if (locazioneRistorante.startsWith(locazione)) {
                ristorantiCorrelati.add(ristoranti.get(i));
            }
        }
        return ristorantiCorrelati;
    }
}
