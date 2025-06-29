package repository;

import repository.generico.HashMapService;
import entita.dominio.Gestore;
import entita.associazioni.Recensione;
import entita.dominio.Ristorante;
import gestioneFile.FileRecensioni;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author armuh
 */
public class RecensioneService extends HashMapService<Integer, Recensione> {

    private static final FileRecensioni FR = new FileRecensioni();

    private AssGestoreRistorantiService assGestoreRistorantiServ = new AssGestoreRistorantiService();
    private static int ID = getID();

    public float mediaStelle(Ristorante r) {
        int somma = 0;
        int ripetizioni = 0;
        String nome = r.getNome();
        for (Recensione recensione : map.values()) {
            if (recensione.getRistoranteRecensito().equals(nome)) {
                somma += recensione.getStelle();
                ripetizioni++;
            }
        }
        try {
            return (float) (somma / ripetizioni);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public double mediaStelle() {
        if (map == null || map.isEmpty()) {
            return 0.0;  // nessuna recensione
        }

        double somma = 0.0;
        for (Recensione r : map.values()) {
            somma += r.getStelle();  // supponendo che getStelle() ritorni un int o double
        }
        return somma / map.size();
    }

    public HashMap<Ristorante, Float> mediaStelle(Gestore gestore) {
        HashMap<Ristorante, Float> mediaStelleMap = new HashMap<>();
        List<Ristorante> listaRistorantiPosseduti = new ArrayList<>();
        try {
            listaRistorantiPosseduti = assGestoreRistorantiServ.get(gestore.getUsername()).getRistorantiList();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        int sommaTot = 0;
        int countTot = 0;
        float mediaStelle = 0;
        for (Ristorante ristoranteP : listaRistorantiPosseduti) {
            int somma = 0;
            int count = 0;
            mediaStelle = 0;
            for (Recensione recensione : map.values()) {
                if (recensione.getRistoranteRecensito().equals(ristoranteP.getNome())) {
                    somma += recensione.getStelle();
                    count++;
                }
            }
            try {
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
