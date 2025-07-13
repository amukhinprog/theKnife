/** Mukhin Artur 760942 CO
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
import java.util.List;

/**
 * Servizio per la gestione della logica di business legata ai ristoranti.
 * Fornisce metodi di controllo sulla proprietà dei locali.
 *
 * @author armuh
 */
public class RistoranteService extends HashMapService<String, Ristorante> {

    private static final FileRistorante FR = new FileRistorante();

    @Override
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
