/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.associazioni.AssGestoreRistoranti;
import entita.dominio.Gestore;
import java.util.ArrayList;
import entita.associazioni.Recensione;
import entita.dominio.Ristorante;
import entita.dominio.Utente;
import gestioneFile.FileGestoreRistorante;
import gestioneFile.FileRecensioni;
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
