/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package gestioneFile;

import entita.associazioni.Recensione;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikoro02
 */
public class FileRecensioni extends GestioneFile<Integer, Recensione> {

    private static String percorsoFile = "..\\data\\recensioni_ristoranti.csv";

    @Override
    public HashMap<Integer, Recensione> ottieniHashMap() {
        List<List<String>> recensioniList = new ArrayList<>();
        HashMap<Integer, Recensione> recension = new HashMap<Integer, Recensione>();
        try {
            recensioniList = FileRecensioni.letturaCsv(percorsoFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRecensioni.class.getName()).log(Level.SEVERE, null, ex);
        }
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            for (List<String> campo : recensioniList) {
                int id = Integer.parseInt(campo.get(0));
                String utente = campo.get(1);
                short stelle = Short.parseShort(campo.get(2));
                String testo = campo.get(3);
                LocalDate data = LocalDate.parse(campo.get(4));
                String ristorante = campo.get(5);
                Recensione recensione1 = new Recensione(id, utente, stelle, testo, data, ristorante);
                recension.put(recensione1.getID(), recensione1);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return recension;
    }

    @Override
    public void scrittura(Recensione recensione) {
        List<String> RecensioniLista = new ArrayList<>();
        RecensioniLista.add(String.valueOf(recensione.getID()));
        RecensioniLista.add(recensione.getUsername());
        RecensioniLista.add(String.valueOf((int) recensione.getStelle()));
        RecensioniLista.add(recensione.getTesto());
        RecensioniLista.add(String.valueOf(recensione.getData()));
        RecensioniLista.add(recensione.getRistoranteRecensito());
        GestioneFile.scrittura(percorsoFile, RecensioniLista);

    }

    @Override
    public void sovraScrivi(HashMap<Integer, Recensione> recensioniMap) {
        LinkedList<List<String>> recensioniLista = new LinkedList<>();
        List<String> recensioneLista = new ArrayList<>();
        for (Recensione recensione : recensioniMap.values()) {
            recensioneLista.add(String.valueOf(recensione.getID()));
            recensioneLista.add(recensione.getUsername());
            recensioneLista.add(String.valueOf(recensione.getStelle()));
            recensioneLista.add("\"" + recensione.getTesto() + "\"");
            recensioneLista.add(recensione.getData().toString());
            recensioneLista.add(recensione.getRistoranteRecensito());
            recensioniLista.add(recensioneLista);
        }
        GestioneFile.sovraScrivi(percorsoFile, recensioniLista);
    }

    public static String getPercorsoFile() {
        return percorsoFile;
    }
}
