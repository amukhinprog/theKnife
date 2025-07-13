/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository;

import repository.generico.HashMapService;
import entita.associazioni.AssGestoreRistoranti;
import entita.associazioni.PreferitiCliente;
import entita.dominio.Gestore;
import entita.dominio.Ristorante;
import gestioneFile.FileGestoreRistorante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class AssGestoreRistorantiService extends HashMapService<String, List<AssGestoreRistoranti>> {

    private static final FileGestoreRistorante FGR = new FileGestoreRistorante();

    /**
     * Verifica se un dato ristorante è già presente nella lista di quelli
     * posseduti da un specifico gestore.
     *
     * @param utente Il gestore da controllare.
     * @param ristorante Il ristorante da verificare.
     * @return true se il gestore possiede già il ristorante, false altrimenti.
     */
    public boolean ristoranteGiaPossedutoDalGestore(Gestore utente, Ristorante ristorante) {
        AssGestoreRistorantiService AGR = new AssGestoreRistorantiService();
        HashMap<String, List<AssGestoreRistoranti>> mappa = AGR.get();
        List<AssGestoreRistoranti> assGestoreRistorantiList = mappa.get(utente.getUsername());
        if (assGestoreRistorantiList == null) {
            return false;
        }
        for (AssGestoreRistoranti assGestoreRistoranti : assGestoreRistorantiList) {
            if (assGestoreRistoranti.getRistorantePosseduto().equals(ristorante.getNome())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Controlla se un ristorante è già di proprietà di un altro gestore.
     *
     * @param utente Il gestore che sta tentando di reclamare il ristorante.
     * @param ristorante Il ristorante da controllare.
     * @return true se il ristorante è già associato a un altro gestore, false
     * altrimenti.
     */
    public boolean ristoranteHaAltroProprietario(Gestore utente, Ristorante ristorante) {
        AssGestoreRistorantiService AGRs = new AssGestoreRistorantiService();
        HashMap<String, List<AssGestoreRistoranti>> mappa = AGRs.get();
        for (String usernameGestore : mappa.keySet()) {
            if (!usernameGestore.equals(utente.getUsername())) {
                for (AssGestoreRistoranti assGestoreRistoranti : mappa.get(usernameGestore)) {
                    if (assGestoreRistoranti.getRistorantePosseduto().equals(ristorante.getNome())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(List<AssGestoreRistoranti> valore) {
        String chiave = getKey(valore);
        List<AssGestoreRistoranti> assGestoreRistorantiList = new ArrayList<>();
        if (map.containsKey(chiave)) {
            assGestoreRistorantiList = map.get(chiave);
        }
        if (valore == null || valore.isEmpty()) {
            return false;
        }
        assGestoreRistorantiList.add(valore.getFirst());
        map.put(chiave, assGestoreRistorantiList);
        scrittura(valore);
        return true;
    }

    @Override
    protected String getKey(List<AssGestoreRistoranti> valore) {
        return valore.getFirst().getUsernameRistoratore();
    }

    @Override
    protected HashMap<String, List<AssGestoreRistoranti>> lettura() {
        return FGR.ottieniHashMap();
    }

    @Override
    protected void scrittura(List<AssGestoreRistoranti> valore) {
        FGR.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<String, List<AssGestoreRistoranti>> map) {
        FGR.sovraScrivi(map);
    }
}
