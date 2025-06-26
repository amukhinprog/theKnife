/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import repository.generico.HashMapService;
import entita.Utente;
import gestioneFile.FileUtenti;
import java.util.HashMap;

/**
 *
 * @author armuh
 */
public class UtenteService extends HashMapService<String, Utente> {

    private static final FileUtenti FU = new FileUtenti();

    @Override
    protected String getKey(Utente utente) {
        return utente.getUsername();
    }

    @Override
    protected HashMap<String, Utente> lettura() {
        return FU.ottieniHashMap();
    }

    @Override
    protected void scrittura(Utente valore) {
        FU.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<String, Utente> map) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
