/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import repository.generico.HashMapService;
import entita.AssGestoreRistoranti;
import entita.Gestore;
import entita.Ristorante;
import gestioneFile.FileRistorante;
import java.util.HashMap;

/**
 *
 * @author armuh
 */
public class RistoranteService extends HashMapService<String, Ristorante> {

    private static final FileRistorante FR = new FileRistorante();

    public boolean ristoranteGiaPossedutoDalGestore(Gestore utente, Ristorante ristorante) {
        AssGestoreRistorantiService AGR = new AssGestoreRistorantiService();
        HashMap<String, AssGestoreRistoranti> mappa = AGR.get();
        AssGestoreRistoranti gestore = mappa.get(utente.getUsername());
        return gestore != null && gestore.contains(ristorante);
    }

    public boolean ristoranteHaAltroProprietario(Gestore utente, Ristorante ristorante) {
        AssGestoreRistorantiService AGRs = new AssGestoreRistorantiService();
        HashMap<String, AssGestoreRistoranti> AGRsmap = AGRs.get();
        for (AssGestoreRistoranti assGestoreRistoranti : AGRsmap.values()) {
            if (assGestoreRistoranti.contains(ristorante)
                    && !assGestoreRistoranti.getUsernameRistoratore().equals(utente.getUsername())) {
                return true;
            }
        }
        return false;
    }

    protected String getKey(Ristorante ristorante) {
        return ristorante.getNome();
    }

    @Override
    protected HashMap<String, Ristorante> lettura() {
        return FR.ottieniHashMap();
    }

    @Override
    protected void scrittura(Ristorante valore) {
        FR.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<String, Ristorante> map) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
