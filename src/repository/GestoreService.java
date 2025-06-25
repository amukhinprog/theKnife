/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import entita.Recensione;
import entita.Utente;
import gestioneFile.FileRecensioni;
import java.util.HashMap;

/**
 *
 * @author Nikoro02
 */
public class GestoreService {

    private RecensioneService recensioneServ = new RecensioneService();

    public double calcolaMediaStelle() {
        HashMap<Integer, Recensione> recension = recensioneServ.getRecensioniHashMap();
        if (recension == null || recension.isEmpty()) {
            return 0.0;  // nessuna recensione
        }

        double somma = 0.0;
        for (Recensione r : recension.values()) {
            somma += r.getStelle();  // supponendo che getStelle() ritorni un int o double
        }

        return somma / recension.size();
    }
}
