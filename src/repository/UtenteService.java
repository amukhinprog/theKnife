/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.Utente;
import gestioneFile.FileUtenti;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author armuh
 */
public class UtenteService {

    private FileUtenti fileUtenti = new FileUtenti();
    private HashMap<String, Utente> utenti = fileUtenti.ottieniHashMap();

    public HashMap<String, Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(HashMap<String, Utente> utenti) {
        this.utenti = utenti;
    }

    public Utente get(String chiave) {
        if (utenti.containsKey(chiave)) {
            return utenti.get(chiave);
        } else {
            return null;
        }
    }

    public boolean containsKey(String chiave) {
        return utenti.containsKey(chiave);
    }
    
    public Collection<Utente> values(){
        return utenti.values();
    }
}
