/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package gestioneFile;

import entita.associazioni.AssGestoreRistoranti;
import entita.dominio.Ristorante;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.AssGestoreRistorantiService;

public class FileGestoreRistorante extends GestioneFile<String, List<AssGestoreRistoranti>> {

    protected static String percorsoFile = "..\\theKnife\\data\\username_ristoranti.csv";

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    @Override
    public void sovraScrivi(HashMap<String, List<AssGestoreRistoranti>> assGestoreRistoranteMap) {
        List<List<String>> righeDaScrivere = new ArrayList<>();
        for (String utente : assGestoreRistoranteMap.keySet()) {
            List<String> riga = new ArrayList<>();
            for (AssGestoreRistoranti assGestoreRistorante : assGestoreRistoranteMap.get(utente)) {
                riga.add(utente);
                riga.add(assGestoreRistorante.getRistorantePosseduto());
                righeDaScrivere.add(riga);
            }
        }
        GestioneFile.sovraScrivi(percorsoFile, righeDaScrivere);
    }

//    public List<Ristorante> ottieniListaRistorantiPossedutiUtenti() {
//        AssGestoreRistorantiService assGestoreRistoranti = new AssGestoreRistorantiService();
//        HashMap<String, AssGestoreRistoranti> assGestoreRistorantiMap = assGestoreRistoranti.get();
//        List<Ristorante> ristorantiPossedutiUtenti = new ArrayList<>();
//
//        List<Ristorante> ristorantiPossedutiDaUtente = new ArrayList<>();
//        for (String chiave : assGestoreRistorantiMap.keySet()) {
//            ristorantiPossedutiDaUtente = assGestoreRistorantiMap.get(chiave).getRistorantiList();
//            ristorantiPossedutiUtenti.addAll(ristorantiPossedutiDaUtente);
//        }
//        return ristorantiPossedutiUtenti;
//    }

    @Override
    public HashMap<String, List<AssGestoreRistoranti>> ottieniHashMap() {
        HashMap<String, List<AssGestoreRistoranti>> assGestoreRistoranteMap = new HashMap<>();
        List<List<String>> assGestoreRistorantiList = new ArrayList<>();
        try {
            assGestoreRistorantiList = FileGestoreRistorante.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileGestoreRistorante.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (List<String> riga : assGestoreRistorantiList) {

            String usernameGestore = riga.get(0);
            String ristorantePosseduto = riga.get(1);
            List<AssGestoreRistoranti> assGestoreRistoranteList = new ArrayList<>();
            AssGestoreRistoranti assGestoreRistorante = new AssGestoreRistoranti(usernameGestore, ristorantePosseduto);
            if (assGestoreRistoranteMap.containsKey(usernameGestore)) {
                assGestoreRistoranteList = assGestoreRistoranteMap.get(usernameGestore);
            }
            assGestoreRistoranteList.add(assGestoreRistorante);
            assGestoreRistoranteMap.put(usernameGestore, assGestoreRistoranteList);
        }
        return assGestoreRistoranteMap;
    }

    @Override
    public void scrittura(List<AssGestoreRistoranti> oggetto) {
        for (AssGestoreRistoranti assGestoreRistorante : oggetto) {
            List<String> rigaDaScrivere = new ArrayList<>();
            rigaDaScrivere.add(assGestoreRistorante.getUsernameRistoratore());
            rigaDaScrivere.add(assGestoreRistorante.getRistorantePosseduto());
            GestioneFile.scrittura(percorsoFile, rigaDaScrivere);
        }
    }

}
