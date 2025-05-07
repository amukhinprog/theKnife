package entita;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
import java.io.*;
import gestioneFile.FileRistorante;
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
                cercaRistorantePerLocazione();
        }
        return new ArrayList<>();
    }

    private static void cercaRistorantePerLocazione() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire il nome della citta': ");
        String locazione = scanner.next();
        List<List<String>> ristoranti = new ArrayList<>();
        int posizioneLocazioneInCsv = 2;
        List<List<String>> ristorantiCorrelati = new ArrayList<>();
        try {
            ristoranti = FileRistorante.letturaCsv(FileRistorante.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        for (int i = 0; i < ristoranti.size(); i++) {
//            System.out.println(ristoranti.get(i).get(posizioneLocazioneInCsv));
            String locazioneRistorante = ristoranti.get(i).get(posizioneLocazioneInCsv).replace("\"", "");
            if (locazioneRistorante.toLowerCase().startsWith(locazione.toLowerCase())) {
                ristorantiCorrelati.add(ristoranti.get(i));
            }
        }
        visualizzaRistorante(ristorantiCorrelati);
    }

    private static void visualizzaRistorante(List<List<String>> ristoranti) {
//        System.out.println(ristoranti);
        int posizioneLocazioneInCsv = 2;
        int posizionePrezzoInCsv = 3;
        int posizioneTipoCucinaInCsv = 4;
        int posizioneDeliveryInCsv = 8;
        int posizionePrenotazioneInCsv = 11;
        for (List<String> ristorante : ristoranti) {
            System.out.println("Locazione: " + ristorante.get(posizioneLocazioneInCsv));
            System.out.println("Prezzo: " + ristorante.get(posizionePrezzoInCsv) + " euro");
            System.out.println("Tipo cucina: " + ristorante.get(posizioneTipoCucinaInCsv));
            System.out.println("Servizio delivery: " + ristorante.get(posizioneDeliveryInCsv));
            System.out.println("Servizio prenotazione: " + ristorante.get(posizionePrenotazioneInCsv) + "\n\n");
        }
    }
}
