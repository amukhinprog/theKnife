package gestioneFile;

import entita.associazioni.PreferitiCliente;
import entita.dominio.Ristorante;
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
public class FilePreferitiCliente extends GestioneFile<String, PreferitiCliente> {

    private static String percorsoFile = "..\\theKnife\\data\\preferiti.csv";

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    @Override
    public HashMap<String, PreferitiCliente> ottieniHashMap() {
        HashMap<String, PreferitiCliente> preferitiMap = new HashMap<>();
        HashMap<String, Ristorante> ristorantiMap = new FileRistorante().ottieniHashMap();

        List<List<String>> preferitiList = new ArrayList<>();
        try {
            preferitiList = FilePreferitiCliente.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FilePreferitiCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (List<String> riga : preferitiList) {
            if (riga.size() < 2) {
                continue;
            }

            String usernameCliente = riga.get(0);
            String[] nomiRistoranti = riga.get(1).split("\\$");

            List<Ristorante> preferiti = new ArrayList<>();
            for (String nome : nomiRistoranti) {
                if (!nome.isBlank()) {
                    Ristorante r = ristorantiMap.get(nome);
                    if (r != null) {
                        preferiti.add(r);
                    }
                }
            }

            PreferitiCliente pc = new PreferitiCliente(usernameCliente, preferiti);
            preferitiMap.put(usernameCliente, pc);
        }

        return preferitiMap;
    }

    @Override
    public void scrittura(PreferitiCliente preferitiCliente) {
        List<String> preferitiList = new ArrayList<>();
        preferitiList.add(preferitiCliente.getUsernameCliente());

        StringBuilder ristorantiConcatenati = new StringBuilder();
        for (Ristorante ristorante : preferitiCliente.getRistorantiPreferiti()) {
            ristorantiConcatenati.append(ristorante.getNome()).append("$");
        }
        preferitiList.add(ristorantiConcatenati.toString());

        GestioneFile.scrittura(getPercorsoFile(), preferitiList);
    }

    public void sovraScrivi(HashMap<String, PreferitiCliente> preferitiMap) {
        LinkedList<List<String>> righeDaScrivere = new LinkedList<>();

        for (PreferitiCliente pc : preferitiMap.values()) {
            List<String> riga = new ArrayList<>();
            riga.add(pc.getUsernameCliente());

            StringBuilder ristorantiConcatenati = new StringBuilder();
            for (Ristorante ristorante : pc.getRistorantiPreferiti()) {
                ristorantiConcatenati.append(ristorante.getNome()).append("$");
            }

            riga.add(ristorantiConcatenati.toString());
            righeDaScrivere.add(riga);
        }

        GestioneFile.sovraScrivi(percorsoFile, righeDaScrivere);
    }
}
