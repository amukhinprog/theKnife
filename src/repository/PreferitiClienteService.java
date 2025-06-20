/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.PreferitiCliente;
import entita.Ristorante;
import gestioneFile.FilePreferitiCliente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class PreferitiClienteService {

    private HashMap<String, PreferitiCliente> preferitiMap = new FilePreferitiCliente().ottieniHashMap();

    public void add(String username, Ristorante ristorante) {
        FilePreferitiCliente file = new FilePreferitiCliente();
        if (preferitiMap.containsKey(username)) {
            PreferitiCliente preferenze = preferitiMap.get(username);
            Collection<PreferitiCliente> preferitiLista = preferitiMap.values();
            boolean assente = true;
            for (PreferitiCliente ristorantePreferito : preferitiLista) {
                if (!ristorantePreferito.getRistorantiPreferiti().contains(ristorante)) {
                    assente = false;
                }
            }
            if (assente) {
                preferenze.addRistorantePreferito(ristorante);
                file.scritturaSuFile(preferenze);
            }
        } else {
            List<Ristorante> nuoviPreferiti = new ArrayList<>();
            nuoviPreferiti.add(ristorante);

            PreferitiCliente nuovaAss = new PreferitiCliente(username, nuoviPreferiti);
            preferitiMap.put(username, nuovaAss);

            file.scritturaSuFile(nuovaAss);
        }
    }

    public void remove(String username, Ristorante ristorante) {
        FilePreferitiCliente file = new FilePreferitiCliente();

        if (preferitiMap.containsKey(username)) {
            PreferitiCliente preferenze = preferitiMap.get(username);

            if (preferenze.getRistorantiPreferiti().contains(ristorante)) {
                preferenze.getRistorantiPreferiti().remove(ristorante);
                file.scritturaSuFile(preferenze);
            }
        }
    }

    public HashMap<String, PreferitiCliente> getPreferitiMap() {
        return preferitiMap;
    }

    public void setPreferitiMap(HashMap<String, PreferitiCliente> preferitiMap) {
        this.preferitiMap = preferitiMap;
    }
}
