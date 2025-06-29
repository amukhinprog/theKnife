/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository;

import repository.generico.HashMapService;
import entita.associazioni.AssGestoreRistoranti;
import gestioneFile.FileGestoreRistorante;
import java.util.HashMap;

/**
 *
 * @author armuh
 */
public class AssGestoreRistorantiService extends HashMapService<String, AssGestoreRistoranti> {

    private static final FileGestoreRistorante FGR = new FileGestoreRistorante();

    @Override
    protected String getKey(AssGestoreRistoranti assGestoreRistoranti) {
        return assGestoreRistoranti.getUsernameRistoratore();
    }

    @Override
    protected HashMap<String, AssGestoreRistoranti> lettura() {
        return FGR.ottieniHashMap();
    }

    @Override
    protected void scrittura(AssGestoreRistoranti valore) {
        FGR.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<String, AssGestoreRistoranti> map) {
        FGR.sovraScrivi(map);
    }
}
