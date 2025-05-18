/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entita;

import gestioneFile.FileGestoreRistorante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranti {

    private List<Ristorante> ristorantiList = new ArrayList<>();
    private static HashMap<String, List<Ristorante>> ristorantiMap = new FileGestoreRistorante().ottieniHashMap();

    public static void assRistoranteAGestore(String username, Ristorante ristorante) {
        if (ristorantiMap.containsKey(username)) {
            List<Ristorante> ristorantiPosseduti = ristorantiMap.get(username);
            ristorantiPosseduti.add(ristorante);
            ristorantiMap.replace(username, ristorantiPosseduti);
        }else{
            List<Ristorante> l = new ArrayList<>();
            l.add(ristorante);
            ristorantiMap.put(username, l);
        }
    }

    public List<Ristorante> getRistorantiList() {
        return ristorantiList;
    }

    public void setRistorantiList(List<Ristorante> ristorantiList) {
        this.ristorantiList = ristorantiList;
    }

}
