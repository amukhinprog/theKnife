/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.associazioni.Recensione;
import entita.associazioni.RispostaRecensioni;
import gestioneFile.FileRispostaRecensioni;
import java.util.HashMap;
import repository.generico.HashMapService;

/**
 *
 * @author armuh
 */
public class RispostaRecensioniService extends HashMapService<Integer, RispostaRecensioni> {

    private static final FileRispostaRecensioni FRR = new FileRispostaRecensioni();
    private static int ID = 0;

    public static int getID() {
        HashMap<Integer, RispostaRecensioni> map = FRR.ottieniHashMap();
        int id = 0;
        for (Integer i : map.keySet()) {
            if (i >= id) {
                id = i;
            }
        }
        return id;
    }

    public static int incID() {
        return ++ID;
    }

    @Override
    protected Integer getKey(RispostaRecensioni valore) {
        return valore.getID();
    }

    @Override
    protected HashMap<Integer, RispostaRecensioni> lettura() {
        return FRR.ottieniHashMap();
    }

    @Override
    protected void scrittura(RispostaRecensioni valore) {
        FRR.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<Integer, RispostaRecensioni> map) {
        FRR.sovraScrivi(map);
    }

}
