/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository;

import repository.generico.HashMapService;
import entita.associazioni.PreferitiCliente;
import gestioneFile.FilePreferitiCliente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class PreferitiClienteService extends HashMapService<String, List<PreferitiCliente>> {

    private static final FilePreferitiCliente FPC = new FilePreferitiCliente();

    @Override
    public boolean add(List<PreferitiCliente> valore) {
        String chiave = getKey(valore);
        List<PreferitiCliente> preferitiClienteList = new ArrayList<>();
        if (map.containsKey(chiave)) {
            preferitiClienteList = map.get(chiave);
        }
        if (valore == null || valore.isEmpty()) {
            return false;
        }
        preferitiClienteList.add(valore.getFirst());
        map.put(chiave, preferitiClienteList);
        scrittura(valore);
        return true;
    }

    @Override
    protected String getKey(List<PreferitiCliente> preferitiCliente) {
        return preferitiCliente.getFirst().getUsernameCliente();
    }

    @Override
    protected HashMap<String, List<PreferitiCliente>> lettura() {
        return FPC.ottieniHashMap();
    }

    @Override
    protected void scrittura(List<PreferitiCliente> valore) {
        FPC.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<String, List<PreferitiCliente>> map) {
        FPC.sovraScrivi(map);
    }
}
