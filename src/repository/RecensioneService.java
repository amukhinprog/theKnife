/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package repository;

import entita.associazioni.AssGestoreRistoranti;
import entita.associazioni.PreferitiCliente;
import repository.generico.HashMapService;
import entita.dominio.Gestore;
import entita.associazioni.Recensione;
import entita.dominio.Ristorante;
import gestioneFile.FileRecensioni;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Servizio per la gestione della logica di business legata alle recensioni.
 * Offre metodi per aggiungere recensioni, calcolare medie e gestire la
 * persistenza.
 *
 * @author armuh
 */
public class RecensioneService extends HashMapService<Integer, Recensione> {

    private static final FileRecensioni FR = new FileRecensioni();

    private AssGestoreRistorantiService assGestoreRistorantiServ = new AssGestoreRistorantiService();
    private static int ID = getID();

    /**
     * Calcola la media delle valutazioni (stelle) per un singolo ristorante.
     *
     * @param ristorante Il ristorante di cui calcolare la media. Non può essere
     * nullo.
     * @return La media delle stelle come numero in virgola mobile, o 0 se non
     * ci sono recensioni.
     */
    public float mediaStelle(String ristorante) {
        int somma = 0;
        int ripetizioni = 0;
        for (Recensione recensione : map.values()) {
            if (recensione.getRistoranteRecensito().equals(ristorante)) {
                somma += recensione.getStelle();
                ripetizioni++;
            }
        }
        try {
            if (ripetizioni == 0) {
                throw new ArithmeticException("Il ristorante non ha recensioni.");
            }
            return (float) (somma / ripetizioni);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public double mediaStelle() {
        if (map == null || map.isEmpty()) {
            return 0.0;
        }

        double somma = 0.0;
        for (Recensione r : map.values()) {
            somma += r.getStelle();
        }
        return somma / map.size();
    }

    /**
     * Calcola la media delle stelle per ogni ristorante posseduto da un dato
     * gestore.
     *
     * @param gestore Il gestore per cui calcolare le medie.
     * @return Una mappa che associa ogni Ristorante del gestore alla sua media
     * di stelle.
     */
    public HashMap<String, Float> mediaStelle(Gestore gestore) {
        HashMap<String, Float> mediaStelleMap = new HashMap<>();
        List<AssGestoreRistoranti> assGestoreRistoranteList = new ArrayList<>();
        try {
            assGestoreRistoranteList = assGestoreRistorantiServ.get(gestore.getUsername());
        } catch (NullPointerException e) {
            System.out.println("Errore: Gestore o ristoranti non trovati.");
        }
        int sommaTot = 0;
        int countTot = 0;
        float mediaStelle = 0;
        for (AssGestoreRistoranti assGestoreRistorante : assGestoreRistoranteList) {
            String ristoranteP = assGestoreRistorante.getRistorantePosseduto();
            int somma = 0;
            int count = 0;
            mediaStelle = 0;

            for (Recensione recensione : map.values()) {
                if (recensione.getRistoranteRecensito().equals(ristoranteP)) {
                    somma += recensione.getStelle();
                    count++;
                }
            }
            try {
                if (count == 0) {
                    throw new ArithmeticException("Il ristorante " + ristoranteP + " non ha recensioni.");
                }
                mediaStelle = somma / count;
            } catch (ArithmeticException e) {
                System.out.println("Il ristorante non ha recensioni");
            }

            mediaStelleMap.put(ristoranteP, mediaStelle);
            sommaTot += somma;
            countTot++;
        }
        //da completare
        return mediaStelleMap;
    }

    public int getNumeroRecensioni(Gestore gestore) {
        List<AssGestoreRistoranti> assGestoreRistoranteList = new ArrayList<>();
        try {
            assGestoreRistoranteList = assGestoreRistorantiServ.get(gestore.getUsername());
        } catch (NullPointerException e) {
            System.out.println("Errore: Gestore o ristoranti non trovati.");
        }
        int count = 0;
        for (AssGestoreRistoranti assGestoreRistoranti : assGestoreRistoranteList) {
            for (Recensione recensione : map.values()) {
                if (recensione.getRistoranteRecensito().equals(assGestoreRistoranti.getRistorantePosseduto())) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    protected Integer getKey(Recensione recensione) {
        return recensione.getID();
    }

    public static int getID() {
        HashMap<Integer, Recensione> map = FR.ottieniHashMap();
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
    public boolean add(Recensione valore) {
        valore.setID(incID());
        Integer chiave = getKey(valore);
        if (map.containsKey(chiave)) {
            throw new RuntimeException("Valore già presente, utilizzare put");
        }
        map.put(chiave, valore);
        scrittura(valore);
        return true;
    }

    public List<Recensione> get(String nomeRistorante) {
        List<Recensione> recensioniList = new ArrayList<>();
        for (Recensione recensione : map.values()) {
            if (recensione.getRistoranteRecensito().equals(nomeRistorante)) {
                recensioniList.add(recensione);
            }
        }
        return recensioniList;
    }

    @Override
    protected HashMap<Integer, Recensione> lettura() {
        return FR.ottieniHashMap();
    }

    @Override
    protected void scrittura(Recensione valore) {
        FR.scrittura(valore);
    }

    @Override
    protected void sovrascrittura(HashMap<Integer, Recensione> map) {
        FR.sovraScrivi(map);
    }

}
