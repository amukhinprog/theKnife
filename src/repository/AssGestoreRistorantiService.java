/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import repository.generico.HashMapService;
import entita.AssGestoreRistoranti;
import entita.Ristorante;
import entita.Utente;
import gestioneFile.FileGestoreRistorante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class AssGestoreRistorantiService extends HashMapService<String, AssGestoreRistoranti> {

    private static final FileGestoreRistorante FGR = new FileGestoreRistorante();

    public void add(Utente utente, Ristorante ristorante) {
        String username = utente.getUsername();
        FileGestoreRistorante fileAss = new FileGestoreRistorante();
        if (ristorante != null) {
            if (map.containsKey(username)) {
                AssGestoreRistoranti g = map.get(username);
                g.addRistorantiList(ristorante);
                map.replace(username, g);
                fileAss.sovraScrivi(map);

            } else {
                List<Ristorante> l = new ArrayList<>();
                l.add(ristorante);
                AssGestoreRistoranti assGestore = new AssGestoreRistoranti(username, l);
                map.put(username, assGestore);

                fileAss.scrittura(assGestore);
            }
        }
    }

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
