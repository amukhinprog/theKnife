/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author armuh
 */
public class FileRistorante {

    private static List<List<String>> aggiungiEliminaCampi(List<List<String>> frasi) throws FileNotFoundException {

        int posizioneCampoPrezzo = 3;
        int posizioneCampoDelivery = 8;
        int posizioneCampoPrenotazione = 11;
        frasi.get(0).remove(10);
        frasi.get(0).remove(10);
        frasi.get(0).remove(10);
        frasi.get(0).add(8, "Delivery");
        frasi.get(0).add(11, "PrenotazioneOnline");
        for (int i = 1; i < frasi.size(); i++) {
//            System.out.println(frasi.get(i).get(posizioneCampoPrezzo));
            frasi.get(i).remove(10);
            frasi.get(i).remove(10);
            frasi.get(i).remove(10);
            int r = (int) (Math.random() * 2);
            frasi.get(i).add(posizioneCampoDelivery, String.valueOf(r));
            r = (int) (Math.random() * 2);
            frasi.get(i).add(posizioneCampoPrenotazione, String.valueOf(r));
            float prezzoMedio = (float) (Math.random() * 15 + Math.random() * 10 + 13);
            float offset = (float) ((Math.random() > 0.5) ? 5 : -5);
            prezzoMedio = Math.round(prezzoMedio * 10 + offset) / 10.0f;
            frasi.get(i).set(3, String.valueOf(prezzoMedio));
//            System.out.println(frasi.get(i));
        }

        System.out.println(frasi.get(0));
        return frasi;
    }

    public static List<List<String>> letturaCsv() throws FileNotFoundException {
        List<List<String>> frasi = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("..\\theKnife\\data\\michelin_my_maps.csv"))) {
            while (scanner.hasNext()) {
                frasi.add(leggiRiga(scanner.nextLine()));
//                System.out.println(frasi.getLast());
            }
        }
//        frasi = aggiungiEliminaCampi(frasi);
        return frasi;
    }

    protected static List<String> leggiRiga(String riga) {
        List<String> rigaSpezzata = new ArrayList<>();
        String campo;
        String parole;
        try (Scanner leggiRiga = new Scanner(riga)) {
            leggiRiga.useDelimiter(",");
            while (leggiRiga.hasNext()) {
                campo = leggiRiga.next();
                if (campo.startsWith("\"") && !campo.endsWith("\"")) {
                    do {
                        parole = leggiRiga.next();
                        campo = campo + "," + parole;
                    } while (!parole.endsWith("\""));
                }
                rigaSpezzata.add(campo);
            }
        }
        return rigaSpezzata;
    }

    public static void scritturaSuFile() {
        File file = new File("..\\theKnife\\data\\michelin_my_maps2.csv");
        List<List<String>> frasi = new ArrayList<>();
        try {
            frasi = letturaCsv();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("..\\theKnife\\data\\michelin_my_maps2.csv"))) {
            for (List<String> riga : frasi) {
                String linea = String.join(",", riga);
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
