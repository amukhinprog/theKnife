/**Mukhin Artur 760942 CO
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
import java.util.LinkedList;
import repository.AssGestoreRistorantiService;

public class FileGestoreRistorante extends GestioneFile<String, AssGestoreRistoranti> {

    protected static String percorsoFile = "..\\theKnife\\data\\username_ristoranti.csv";

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    @Override
    public void sovraScrivi(HashMap<String, AssGestoreRistoranti> assRistorantiGestore) {
        LinkedList<List<String>> assRistorantiGestoreList = new LinkedList<>();
        for (String chiave : assRistorantiGestore.keySet()) {
            List<String> riga = new ArrayList<>();
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
        for (String chiave : assGestoreRistorantiMap.keySet()) {
            ristorantiPossedutiDaUtente = assGestoreRistorantiMap.get(chiave).getRistorantiList();
            ristorantiPossedutiUtenti.addAll(ristorantiPossedutiDaUtente);
        }
        return ristorantiPossedutiUtenti;
    }

    @Override
    public HashMap<String, AssGestoreRistoranti> ottieniHashMap() {
        HashMap<String, AssGestoreRistoranti> assGestoreRistoranteMap = new HashMap<>();

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
