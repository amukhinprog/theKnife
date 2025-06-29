/**Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository;

import entita.associazioni.AssGestoreRistoranti;
import entita.dominio.Gestore;
import entita.dominio.Ristorante;
import gestioneFile.FileGestoreRistorante;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Nikoro02
 */
public class GestoreService {

    private RecensioneService recensioneServ = new RecensioneService();
    private FileGestoreRistorante FGR = new FileGestoreRistorante();
    private HashMap<String, AssGestoreRistoranti> gesRisMap = FGR.ottieniHashMap();
    private AssGestoreRistorantiService assGestoreRistorantiServ = new AssGestoreRistorantiService();

    public GestoreService() {
    }

    

    public HashMap<Ristorante, Float> mediaStelle(Gestore gestore) {
        AssGestoreRistoranti AGR = assGestoreRistorantiServ.get().get(gestore.getUsername());
        List<Ristorante> listaRistoranti = AGR.getRistorantiList();
        HashMap<Ristorante, Float> mediaStelleMap = new HashMap<Ristorante, Float>();
        for (Ristorante ristorante : listaRistoranti) {
            float media = recensioneServ.mediaStelle(ristorante);
            mediaStelleMap.put(ristorante, media);
        }
        return mediaStelleMap;
    }

    public HashMap<String, AssGestoreRistoranti> getGesRisMap() {
        return gesRisMap;
    }

    public void setGesRisMap(HashMap<String, AssGestoreRistoranti> gesRisMap) {
        this.gesRisMap = gesRisMap;
    }

}
