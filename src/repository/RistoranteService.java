/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository;

import repository.generico.HashMapService;
import entita.associazioni.AssGestoreRistoranti;
import entita.dominio.Gestore;
import entita.dominio.Ristorante;
import gestioneFile.FileRistorante;
import java.util.HashMap;

/**
 * Servizio per la gestione della logica di business legata ai ristoranti.
 * Fornisce metodi di controllo sulla proprietà dei locali.
 * @author armuh
 */
public class RistoranteService extends HashMapService<String, Ristorante> {

    private static final FileRistorante FR = new FileRistorante();

 /**
 * Verifica se un dato ristorante è già presente nella lista di quelli
 * posseduti da un specifico gestore.
 * @param utente Il gestore da controllare.
 * @param ristorante Il ristorante da verificare.
 * @return true se il gestore possiede già il ristorante, false altrimenti.
 */
    public boolean ristoranteGiaPossedutoDalGestore(Gestore utente, Ristorante ristorante) {
        AssGestoreRistorantiService AGR = new AssGestoreRistorantiService();
        HashMap<String, AssGestoreRistoranti> mappa = AGR.get();
        AssGestoreRistoranti gestore = mappa.get(utente.getUsername());
        return gestore != null && gestore.contains(ristorante);
    }
/**
 * Controlla se un ristorante è già di proprietà di un altro gestore.
 * @param utente Il gestore che sta tentando di reclamare il ristorante.
 * @param ristorante Il ristorante da controllare.
 * @return true se il ristorante è già associato a un altro gestore, false altrimenti.
 */
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
