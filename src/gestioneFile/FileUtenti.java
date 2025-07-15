/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package gestioneFile;

import entita.dominio.Cliente;
import entita.dominio.Gestore;
import entita.dominio.Utente;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestisce la lettura e scrittura dei dati degli Utenti dal file utenti.csv.
 * Implementa i metodi astratti di GestioneFile per operare specificamente con
 * oggetti di tipo Utente, Cliente e Gestore.
 * @author armuh
 */
public class FileUtenti extends GestioneFile<String, Utente> {

    private static String percorsoFile = "..\\data\\utenti.csv";

    public static String getPercorsoFile() {
        return percorsoFile;
    }

    @Override
    public void scrittura(Utente utente) {
        List<String> utenteList = new ArrayList<>();
        utenteList.add(utente.getNome());
        utenteList.add(utente.getCognome());
        utenteList.add(utente.getUsername());
        utenteList.add(utente.getPassword());
        utenteList.add(utente.getDataNascita().toString());
        utenteList.add(utente.getLuogodomicilio());
        utenteList.add(utente.getRuolo());

        GestioneFile.scrittura(getPercorsoFile(), utenteList);
    }

    @Override
    public HashMap<String, Utente> ottieniHashMap() {
        List<List<String>> utentiList = new ArrayList<>();
        try {
            utentiList = FileUtenti.letturaCsv(FileUtenti.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtenti.class.getName()).log(Level.SEVERE, null, ex);
        }
        HashMap<String, Utente> utenti = new HashMap<String, Utente>();
        for (List<String> utenteList : utentiList) {
            Utente utente = null;
            if (utenteList.get(6).equals("gestore")) {
                utente = new Gestore(utenteList.get(0), utenteList.get(1),
                        utenteList.get(2), utenteList.get(3),
                        LocalDate.parse(utenteList.get(4)), utenteList.get(5),
                        utenteList.get(6));
            } else if (utenteList.get(6).equals("cliente")) {
                utente = new Cliente(utenteList.get(0), utenteList.get(1),
                        utenteList.get(2), utenteList.get(3),
                        LocalDate.parse(utenteList.get(4)), utenteList.get(5),
                        utenteList.get(6));
            }
            utenti.put(utente.getUsername(), utente);
        }
        return utenti;
    }
/**
 * Legge il file degli utenti e restituisce una mappa contenente solo gli utenti
 * che hanno il ruolo di "gestore".
 * @return Una HashMap contenente solo gli oggetti Gestore, con l'username come chiave.
 */
    public HashMap<String, Gestore> ottieniHashMapGestori() {
        List<List<String>> utentiList = new ArrayList<>();
        try {
            utentiList = FileUtenti.letturaCsv(FileUtenti.getPercorsoFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtenti.class.getName()).log(Level.SEVERE, null, ex);
        }
        HashMap<String, Gestore> gestoriMap = new HashMap<String, Gestore>();
        for (List<String> utenteList : utentiList) {
            Gestore gestore = null;
            if (utenteList.get(6).equals("gestore")) {
                gestore = new Gestore(utenteList.get(0), utenteList.get(1),
                        utenteList.get(2), utenteList.get(3),
                        LocalDate.parse(utenteList.get(4)), utenteList.get(5),
                        utenteList.get(6));

                gestoriMap.put(gestore.getUsername(), gestore);
            }
        }
        return gestoriMap;
    }

    @Override
    public void sovraScrivi(HashMap<String, Utente> map) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
