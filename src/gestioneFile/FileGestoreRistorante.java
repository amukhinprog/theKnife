/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import entita.AssGestoreRistoranti;
import entita.Gestore;
import entita.Utente;
import entita.ListaRistoranti;
import entita.Ristorante;
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

public class FileGestoreRistorante extends GestioneFile<String, AssGestoreRistoranti> {

    protected static String percorsoFile = "..\\theKnife\\data\\username_ristoranti.csv";

//    public static void associaGestore(String username, String nomeRistorante) {
//        List<List<String>> gestori = new ArrayList<>();
//        try {
//            gestori = FileUtenti.letturaCsv(FileUtenti.getPercorsoFile());
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(FileGestoreRistorante.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        for (int i = 1; i < gestori.size(); i++) {
//            if (gestori.get(i).get(2).equals(username)) {
//                String campo = gestori.get(i).get(1).concat("$ " + nomeRistorante);
//                gestori.get(i).set(1, campo);
//                sovraScriFile(getPercorsoFile(), gestori);
//            } else {
//                //aggiungere una riga username, e suo ristorante
//                List<String> gestore = new ArrayList<>();
//                gestore.add(username);
//                gestore.add(nomeRistorante);
//                FileGestoreRistorante.scritturaSuFile(getPercorsoFile(), gestore);
//
//            }
//        }
//    }
    public static String getPercorsoFile() {
        return percorsoFile;
    }

    public void sovraScriFile(HashMap<String, AssGestoreRistoranti> assRistorantiGestore) {
        File file = new File(percorsoFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoFile))) {
            List<String> riga = new ArrayList<>();
            for (String chiave : assRistorantiGestore.keySet()) {
                List<Ristorante> ristorantiPosseduti = assRistorantiGestore.get(chiave).getRistorantiList();
                riga.add(assRistorantiGestore.get(chiave).getUsernameRistoratore());
                for (Ristorante ristorantePosseduto : ristorantiPosseduti) {
                    riga.add(ristorantePosseduto.getNome() + "$");
                }
            }
            String linea = String.join(",", riga);
            writer.write(linea);
            writer.newLine();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Ristorante> ottieniListaRistorantiPossedutiUtenti() {
        List<List<String>> assGestoreRistoranteList = new ArrayList<>();
        HashMap<String, Ristorante> ristorantiMap = new FileRistorante().ottieniHashMap();
        try {
            assGestoreRistoranteList = FileGestoreRistorante.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileGestoreRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Ristorante> ristorantiPossedutiUtenti = new ArrayList<>();
        for (List<String> riga : assGestoreRistoranteList) {

            String[] ristorantiSplittati = riga.get(1).split("\\$");
            for (String nomeRistorante : ristorantiSplittati) {
                Ristorante ristorante = ristorantiMap.get(nomeRistorante);
                ristorantiPossedutiUtenti.add(ristorante);
            }
        }
        return ristorantiPossedutiUtenti;
    }

    @Override
    public HashMap<String, AssGestoreRistoranti> ottieniHashMap() {
        HashMap<String, AssGestoreRistoranti> assGestoreRistoranteMap = new HashMap<>();
        HashMap<String, Gestore> gestoriMap = new FileUtenti().ottieniHashMapGestori();

        HashMap<String, Ristorante> ristorantiMap = new FileRistorante().ottieniHashMap();

        List<List<String>> assGestoreRistoranteList = new ArrayList<>();
        try {
            assGestoreRistoranteList = FileGestoreRistorante.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileGestoreRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (List<String> riga : assGestoreRistoranteList) {

            String usernameGestore = riga.get(0);
            String[] ristorantiSplittati = riga.get(1).split("\\$");
            List<Ristorante> l = new ArrayList<>();
            for (String nomeRistorante : ristorantiSplittati) {
                Ristorante ristorante = ristorantiMap.get(nomeRistorante);
                l.add(ristorante);
            }
            AssGestoreRistoranti ristoratoreConRistoranti = new AssGestoreRistoranti(usernameGestore, l);
            assGestoreRistoranteMap.put(usernameGestore, ristoratoreConRistoranti);
        }
        return assGestoreRistoranteMap;
    }

    @Override
    public void scritturaSuFile(AssGestoreRistoranti assRistorantiGestore) {
        List<String> assRistorantiGestoreList = new ArrayList<>();
        assRistorantiGestoreList.add(assRistorantiGestore.getUsernameRistoratore());
        for (entita.Ristorante ristorantePosseduto : assRistorantiGestore.getRistorantiList()) {
            String nomeRistorantePosseduto = ristorantePosseduto.getNome() + "$";
            assRistorantiGestoreList.add(nomeRistorantePosseduto);
        }
        GestioneFile.scritturaSuFile(percorsoFile, assRistorantiGestoreList);
        HashMap<String, AssGestoreRistoranti> ristorantiMap = ottieniHashMap();
        assRistorantiGestore.setRistorantiMap(ristorantiMap);
    }

}
