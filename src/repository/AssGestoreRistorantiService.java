/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.AssGestoreRistoranti;
import entita.Ristorante;
import gestioneFile.FileGestoreRistorante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class AssGestoreRistorantiService {

    private HashMap<String, AssGestoreRistoranti> ristorantiMap = new FileGestoreRistorante().ottieniHashMap();

    public void add(String username, Ristorante ristorante) {
        FileGestoreRistorante fileAss = new FileGestoreRistorante();
        if (ristorante != null) {
            if (ristorantiMap.containsKey(username)) {
                AssGestoreRistoranti g = ristorantiMap.get(username);
                g.addRistorantiList(ristorante);
                ristorantiMap.replace(username, g);
                fileAss.sovraScrivi(ristorantiMap);

            } else {
                List<Ristorante> l = new ArrayList<>();
                l.add(ristorante);
                AssGestoreRistoranti assGestore = new AssGestoreRistoranti(username, l);
                ristorantiMap.put(username, assGestore);

                fileAss.scrittura(assGestore);
            }
        }
    }

    public HashMap<String, AssGestoreRistoranti> getRistorantiMap() {
        return ristorantiMap;
    }

    public void setRistorantiMap(HashMap<String, AssGestoreRistoranti> ristorantiMap) {
        this.ristorantiMap = ristorantiMap;
    }
    
}
