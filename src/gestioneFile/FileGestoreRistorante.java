/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import entita.Utente;
import entita.ListaRistoranti;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileGestoreRistorante<Gestore, Ristorante> extends GestioneFile {

    protected static String percorsoFile = "..\\theKnife\\data\\username_ristoranti.csv";

    public static void associaGestore(String username, String nomeRistorante) {
        List<List<String>> gestori = new ArrayList<>();
        try {
            gestori = FileUtenti.letturaCsv(FileUtenti.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileGestoreRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 1; i < gestori.size(); i++) {
            if (gestori.get(i).get(2).equals(username)) {
                String campo = gestori.get(i).get(1).concat("$ " + nomeRistorante);
                gestori.get(i).set(1, campo);
                sovraScriFile(getPercorsoFile(), gestori);
            } else {
                //aggiungere una riga username, e suo ristorante
                List<String> gestore = new ArrayList<>();
                gestore.add(username);
                gestore.add(nomeRistorante);
                FileGestoreRistorante.scritturaSuFile(getPercorsoFile(), gestore);

            }
        }
    }

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    public static void sovraScriFile(String percorsoFile, List<List<String>> oggetti) {
        File file = new File(percorsoFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoFile))) {
            for (List<String> oggetto : oggetti) {
                String linea = String.join(",", oggetto);
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public HashMap<entita.Gestore, List<entita.Ristorante>> ottieniHashMap() {
        HashMap<entita.Gestore, List<entita.Ristorante>> assGestoreRistoranteMap = new HashMap<>();
        HashMap<String, entita.Gestore> gestoriMap = new FileUtenti().ottieniHashMapGestori();

        HashMap<String, entita.Ristorante> ristorantiMap = new FileRistorante().ottieniHashMap();

        List<List<String>> assGestoreRistoranteList = new ArrayList<>();
        try {
            assGestoreRistoranteList = FileGestoreRistorante.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileGestoreRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (List<String> riga : assGestoreRistoranteList) {
            entita.Gestore gestore =  gestoriMap.get(riga.get(0));
            String[] ristorantiSplittati = riga.get(1).split("\\$");
            List<entita.Ristorante> l = new ArrayList<>();
            for (String nomeRistorante : ristorantiSplittati) {
                entita.Ristorante ristorante = ristorantiMap.get(nomeRistorante);
                l.add(ristorante);
            }
            assGestoreRistoranteMap.put(gestore, l);
        }
        return assGestoreRistoranteMap;
    }

    @Override
    public void scritturaSuFile(Object oggetto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
