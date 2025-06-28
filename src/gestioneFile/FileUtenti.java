/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 *
 * @author armuh
 */
public class FileUtenti extends GestioneFile<String, Utente> {

    private static String percorsoFile = "..\\theKnife\\data\\utenti.csv";
//    private UtenteService utenteServ = new UtenteService();
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
