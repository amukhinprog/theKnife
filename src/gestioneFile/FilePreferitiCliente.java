/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package gestioneFile;

import entita.associazioni.PreferitiCliente;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;

/**
 *
 * @author filod
 */
public class FilePreferitiCliente extends GestioneFile<String, List<PreferitiCliente>> {

    private static final String percorsoFile = "..\\theKnife\\data\\preferiti.csv";

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    @Override
    public HashMap<String, List<PreferitiCliente>> ottieniHashMap() {
        HashMap<String, List<PreferitiCliente>> preferitiMap = new HashMap<>();

        List<List<String>> preferitiListAll = new ArrayList<>();
        try {
            preferitiListAll = FilePreferitiCliente.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilePreferitiCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (List<String> riga : preferitiListAll) {
            if (riga.size() < 2) {
                continue;
            }

            String usernameCliente = riga.get(0);
            String nomeRistorante = riga.get(1);
            PreferitiCliente pc = new PreferitiCliente(usernameCliente, nomeRistorante);
            List<PreferitiCliente> preferitiList = new ArrayList<>();
            if (preferitiMap.containsKey(usernameCliente)) {
                preferitiList = preferitiMap.get(usernameCliente);
                preferitiList.add(pc);
            }
            preferitiList.add(pc);
            preferitiMap.put(usernameCliente, preferitiList);
        }
        return preferitiMap;
    }

    @Override
    public void scrittura(List<PreferitiCliente> preferitiCliente) {
        for (PreferitiCliente preferito : preferitiCliente) {
            List<String> preferitiList = new ArrayList<>();
            preferitiList.add(preferito.getUsernameCliente());
            preferitiList.add(preferito.getRistorantePreferito());
            GestioneFile.scrittura(percorsoFile, preferitiList);
        }
    }

    @Override
    public void sovraScrivi(HashMap<String, List<PreferitiCliente>> preferitiMap) {
        List<List<String>> righeDaScrivere = new LinkedList<>();
        for (String utente : preferitiMap.keySet()) {
            List<String> riga = new ArrayList<>();
            for (PreferitiCliente preferitiCliente : preferitiMap.get(utente)) {
                riga.add(utente);
                riga.add(preferitiCliente.getRistorantePreferito());
                righeDaScrivere.add(riga);
            }
        }
        GestioneFile.sovraScrivi(percorsoFile, righeDaScrivere);
    }
}
