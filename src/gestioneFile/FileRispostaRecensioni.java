/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package gestioneFile;

import entita.associazioni.RispostaRecensioni;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author armuh
 */
public class FileRispostaRecensioni extends GestioneFile<String, List<RispostaRecensioni>> {

    private static String percorsoFile = "..\\theKnife\\data\\risposta_recensioni.csv";

    @Override
    public HashMap<String, List<RispostaRecensioni>> ottieniHashMap() {
        List<List<String>> risposteList = new ArrayList<>();
        HashMap<String, List<RispostaRecensioni>> risposteMap = new HashMap<>();
        try {
            risposteList = FileRecensioni.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            for (List<String> campo : risposteList) {

                int ID = Integer.parseInt(campo.get(0));
                int idRif = Integer.parseInt(campo.get(1));
                String username = campo.get(2);
                String testo = campo.get(3);
                LocalDate data = LocalDate.parse(campo.get(4));
                RispostaRecensioni rispostaRecensioni = new RispostaRecensioni(ID, idRif, username, testo, data);
                List<RispostaRecensioni> values = new ArrayList<>();
                if (risposteMap.containsKey(username)) {
                    values = risposteMap.get(username);
                }
                values.add(rispostaRecensioni);
                risposteMap.put(username, values);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return risposteMap;
    }

    @Override
    public void scrittura(List<RispostaRecensioni> oggetto) {
        List<String> rispostaRecensioniLista = new ArrayList<>();
        for (RispostaRecensioni rispostaRecensioni : oggetto) {
            rispostaRecensioniLista.add(String.valueOf(rispostaRecensioni.getID()));
            rispostaRecensioniLista.add(String.valueOf(rispostaRecensioni.getIdRif()));
            rispostaRecensioniLista.add(rispostaRecensioni.getUsername());
            rispostaRecensioniLista.add(rispostaRecensioni.getTesto());
            rispostaRecensioniLista.add(String.valueOf(rispostaRecensioni.getData()));
            GestioneFile.scrittura(percorsoFile, rispostaRecensioniLista);
        }
    }

    @Override
    public void sovraScrivi(HashMap<String, List<RispostaRecensioni>> map) {
        LinkedList<List<String>> righeDaScrivere = new LinkedList<>();
        for (String username : map.keySet()) {
            List<String> riga = new ArrayList<>();
            for (RispostaRecensioni rispostaRecensioni : map.get(username)) {
                riga.add(String.valueOf(rispostaRecensioni.getID()));
                riga.add(String.valueOf(rispostaRecensioni.getIdRif()));
                riga.add(rispostaRecensioni.getUsername());
                riga.add(rispostaRecensioni.getTesto());
                riga.add(String.valueOf(rispostaRecensioni.getData()));
                righeDaScrivere.add(riga);
            }
        }
        GestioneFile.sovraScrivi(percorsoFile, righeDaScrivere);
    }

}
