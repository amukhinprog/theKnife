/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestioneFile;

import entita.associazioni.AssGestoreRistoranti;
import entita.dominio.Gestore;
import entita.dominio.Ristorante;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import repository.AssGestoreRistorantiService;


public class FileGestoreRistorante extends GestioneFile<String, AssGestoreRistoranti> {

    protected static String percorsoFile = "..\\theKnife\\data\\username_ristoranti.csv";
    private final List<String> intestazione = new ArrayList<>(List.of("username", "ristorantiPosseduti"));
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

    public void sovraScrivi(HashMap<String, AssGestoreRistoranti> assRistorantiGestore) {

        LinkedList<List<String>> assRistorantiGestoreList = new LinkedList<>();
        assRistorantiGestoreList.add(intestazione);

        List<String> riga = new ArrayList<>();
        for (String chiave : assRistorantiGestore.keySet()) {
            List<Ristorante> ristorantiPosseduti = assRistorantiGestore.get(chiave).getRistorantiList();
            riga.add(assRistorantiGestore.get(chiave).getUsernameRistoratore());
            String stringaDiRistoranti = "";
            for (Ristorante ristorantePosseduto : ristorantiPosseduti) {
                stringaDiRistoranti += ristorantePosseduto.getNome() + "$";
            }
            riga.add(stringaDiRistoranti);
            assRistorantiGestoreList.add(riga);
        }
        GestioneFile.sovraScrivi(percorsoFile, assRistorantiGestoreList);
    }

    public List<Ristorante> ottieniListaRistorantiPossedutiUtenti() {
        AssGestoreRistorantiService assGestoreRistoranti = new AssGestoreRistorantiService();
        HashMap<String, AssGestoreRistoranti> assGestoreRistorantiMap = assGestoreRistoranti.get();
        List<Ristorante> ristorantiPossedutiUtenti = new ArrayList<>();
        
        List<Ristorante> ristorantiPossedutiDaUtente = new ArrayList<>(); 
        for(String chiave: assGestoreRistorantiMap.keySet()){
            ristorantiPossedutiDaUtente = assGestoreRistorantiMap.get(chiave).getRistorantiList();
            ristorantiPossedutiUtenti.addAll(ristorantiPossedutiDaUtente);
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
    public void scrittura(AssGestoreRistoranti assRistorantiGestore) {
        List<String> assRistorantiGestoreList = new ArrayList<>();
        assRistorantiGestoreList.add(assRistorantiGestore.getUsernameRistoratore());
        for (entita.dominio.Ristorante ristorantePosseduto : assRistorantiGestore.getRistorantiList()) {
            String nomeRistorantePosseduto = ristorantePosseduto.getNome() + "$";
            assRistorantiGestoreList.add(nomeRistorantePosseduto);
        }
        GestioneFile.scrittura(percorsoFile, assRistorantiGestoreList);
    }

}
