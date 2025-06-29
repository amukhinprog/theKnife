package repository;

import repository.generico.HashMapService;
import entita.associazioni.PreferitiCliente;
import gestioneFile.FilePreferitiCliente;
import java.util.HashMap;

/**
 *
 * @author armuh
 */
public class PreferitiClienteService extends HashMapService<String, PreferitiCliente> {

    private static final FilePreferitiCliente FPC = new FilePreferitiCliente();

    @Override
    protected String getKey(PreferitiCliente preferitiCliente) {
        return preferitiCliente.getUsernameCliente();
    }

    @Override
    protected HashMap<String, PreferitiCliente> lettura() {
        return FPC.ottieniHashMap();
    }

    @Override
    protected void scrittura(PreferitiCliente valore) {
        FPC.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<String, PreferitiCliente> map) {
        FPC.sovraScrivi(map);
    }
}
