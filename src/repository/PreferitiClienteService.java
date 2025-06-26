/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import repository.generico.HashMapService;
import entita.PreferitiCliente;
import entita.Ristorante;
import entita.Utente;
import gestioneFile.FilePreferitiCliente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class PreferitiClienteService extends HashMapService<String, PreferitiCliente> {

    private static final FilePreferitiCliente FPC = new FilePreferitiCliente();

    public void add(Utente utente, Ristorante ristorante) {
        FilePreferitiCliente file = new FilePreferitiCliente();
        String username = utente.getUsername();
        if (map.containsKey(username)) {
            PreferitiCliente preferenze = map.get(username);
            Collection<PreferitiCliente> preferitiLista = map.values();
            boolean assente = true;
            for (PreferitiCliente ristorantePreferito : preferitiLista) {
                if (ristorantePreferito.getRistorantiPreferiti().contains(ristorante)) {
                    assente = false;
                }
            }
            if (assente) {
                preferenze.addRistorantePreferito(ristorante);
                map.put(username, preferenze);
                file.sovraScrivi(map);
            }
        } else {
            List<Ristorante> nuoviPreferiti = new ArrayList<>();
            nuoviPreferiti.add(ristorante);

            PreferitiCliente nuovaAss = new PreferitiCliente(username, nuoviPreferiti);
            map.put(username, nuovaAss);

            file.scrittura(nuovaAss);
        }
    }

    public void remove(Utente utente, Ristorante ristorante) {
        FilePreferitiCliente file = new FilePreferitiCliente();
        String username = utente.getUsername();
        if (map.containsKey(username)) {
            PreferitiCliente preferenze = map.get(username);

            if (preferenze.getRistorantiPreferiti().contains(ristorante)) {
                preferenze.getRistorantiPreferiti().remove(ristorante);
                map.put(username, preferenze);
                file.sovraScrivi(map);
            }
        }
    }

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
