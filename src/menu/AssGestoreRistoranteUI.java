/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.associazioni.AssGestoreRistoranti;
import entita.dominio.Gestore;
import entita.dominio.Ristorante;
import entita.dominio.Utente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import repository.AssGestoreRistorantiService;
import repository.RistoranteService;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranteUI implements ComandiUI<Utente, List<AssGestoreRistoranti>> {

    AssGestoreRistorantiService assGestoreRistorantiServ;
    Scanner scanner;
    RistoranteService ristoranteServ;

    public AssGestoreRistoranteUI(Scanner scanner, AssGestoreRistorantiService assGestoreRistorantiServ, RistoranteService ristoranteServ) {
        this.scanner = scanner;
        this.assGestoreRistorantiServ = assGestoreRistorantiServ;
        this.ristoranteServ = ristoranteServ;
    }

    public Ristorante chiediRistorante() {
        String nomeRistorante;
        scanner.nextLine();
        do {
            System.out.println("Scrivere il nome del ristorante (oppure 0 per annullare): ");
            nomeRistorante = scanner.nextLine();
            if (nomeRistorante.equals("0")) {
                return null;
            }
        } while (!ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);
    }

    @Override
    public boolean add(Utente utente) {
        Ristorante ristorante = chiediRistorante();
        if (ristorante == null) {
            System.out.println("Operazione annullata.");
            return false;
        }
        if (assGestoreRistorantiServ.ristoranteGiaPossedutoDalGestore((Gestore) utente, ristorante)) {
            System.out.println(" Il ristorante \"" + ristorante.getNome() + "\" è già in suo possesso.");
            return false;
        }
        if (assGestoreRistorantiServ.ristoranteHaAltroProprietario((Gestore) utente, ristorante)) {
            System.out.println(" Il ristorante \"" + ristorante.getNome() + "\" è già in possesso da un altro gestore.");
            return false;
        }
        AssGestoreRistoranti assGestoreRistoranti = new AssGestoreRistoranti(utente.getUsername(), ristorante.getNome());
        List<AssGestoreRistoranti> assGestoreRistorantiList = new ArrayList<>();
        assGestoreRistorantiList.add(assGestoreRistoranti);
        boolean b = assGestoreRistorantiServ.add(assGestoreRistorantiList);
        if (b) {
            System.out.println("Il ristorante \"" + ristorante.getNome() + "\" è stato aggiunto alla tua lista.");
        }
        return b;
    }

    @Override
    public List<AssGestoreRistoranti> get(Utente valore) {
        return assGestoreRistorantiServ.get(valore.getUsername());
    }

    @Override
    public boolean remove(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean put(Utente valore) {
        Ristorante ristorante = chiediRistorante();
        if (assGestoreRistorantiServ.ristoranteGiaPossedutoDalGestore((Gestore) valore, ristorante)) {
            System.out.println(" Il ristorante \"" + ristorante.getNome() + "\" è già in suo possesso.");
            return false;
        }
        if (assGestoreRistorantiServ.ristoranteHaAltroProprietario((Gestore) valore, ristorante)) {
            System.out.println(" Il ristorante \"" + ristorante.getNome() + "\" è già in possesso da un altro gestore.");
            return false;
        }

        AssGestoreRistoranti assGestoreRistoranti = new AssGestoreRistoranti(valore.getUsername(), ristorante.getNome());
        List<AssGestoreRistoranti> assGestoreRistorantiList = new ArrayList<>();
        assGestoreRistorantiList.add(assGestoreRistoranti);
        boolean b = assGestoreRistorantiServ.put(valore.getUsername(), assGestoreRistorantiList);
        if (b) {
            System.out.println("Il ristorante " + ristorante.getNome() + " e' stato aggiunto alla sua lista");
            return true;
        } else {
            System.out.println("Il ristorante " + ristorante.getNome() + " non e' stato aggiunto alla sua lista");
            return false;
        }
    }

    @Override
    public void visualizza() {
        System.out.println("Ristoranti associati:");
        HashMap<String, List<AssGestoreRistoranti>> mappa = assGestoreRistorantiServ.get();
        for (String username : mappa.keySet()) {
            System.out.println("Username: " + username);
            for (AssGestoreRistoranti assGestoreRistoranti : mappa.get(username)) {
                System.out.println("- " + assGestoreRistoranti.getRistorantePosseduto());
            }
        }
    }

    @Override
    public void visualizza(List<AssGestoreRistoranti> valore) {
        System.out.println("Username: " + valore.getFirst().getUsernameRistoratore());
        for (AssGestoreRistoranti assGestoreRistoranti : valore) {
            System.out.println("- " + assGestoreRistoranti.getRistorantePosseduto());
        }
    }

    @Override
    public void visualizza(Utente chiave) {
        List<AssGestoreRistoranti> associazione = assGestoreRistorantiServ.get(chiave.getUsername());
        if (associazione != null) {
            visualizza(associazione);
        } else {
            System.out.println("Nessun ristorante trovato per questo gestore.");
        }
    }
}
