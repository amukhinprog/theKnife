/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository;

import entita.associazioni.RispostaRecensioni;
import gestioneFile.FileRispostaRecensioni;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import repository.generico.HashMapService;

/**
 *
 * @author armuh
 */
public class RispostaRecensioniService extends HashMapService<String, List<RispostaRecensioni>> {

    private static final FileRispostaRecensioni FRR = new FileRispostaRecensioni();
    private static int ID = getID();

    public static int getID() {
        HashMap<String, List<RispostaRecensioni>> map = FRR.ottieniHashMap();
        int id = 0;
        for (String username : map.keySet()) {
            for (RispostaRecensioni rispostaRecensioni : map.get(username)) {
                if (rispostaRecensioni.getID() >= id) {
                    id = rispostaRecensioni.getID();
                }
            }
        }
        return id;
    }

    public static int incID() {
        return ++ID;
    }

    @Override
    public boolean add(List<RispostaRecensioni> valore) {
        valore.getFirst().setID(incID());
        String chiave = getKey(valore);
        List<RispostaRecensioni> rispostaRecensioniList = new ArrayList<>();
        if (map.containsKey(chiave)) {
            rispostaRecensioniList = map.get(chiave);
        }
        if (valore == null || valore.isEmpty()) {
            return false;
        }
        rispostaRecensioniList.add(valore.getFirst());
        map.put(chiave, rispostaRecensioniList);
        scrittura(valore);
        return true;
    }

    @Override
    protected void scrittura(List<RispostaRecensioni> valore) {
        FRR.scrittura(valore);
    }

    @Override
    protected String getKey(List<RispostaRecensioni> valore) {
        return valore.getFirst().getUsername();
    }

    @Override
    protected HashMap<String, List<RispostaRecensioni>> lettura() {
        return FRR.ottieniHashMap();
    }

    @Override
    protected void sovrascrittura(HashMap<String, List<RispostaRecensioni>> map) {
        FRR.sovraScrivi(map);
    }

}
