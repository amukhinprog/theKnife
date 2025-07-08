/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.associazioni.AssGestoreRistoranti;
import entita.dominio.Ristorante;
import entita.dominio.Utente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import repository.AssGestoreRistorantiService;
import repository.RistoranteService;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranteUI implements ComandiUI<Utente, AssGestoreRistoranti> {

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

    public void aggiungi(Utente utente) {
        if (get(utente) != null) {
            put(utente);
        } else {
            add(utente);
        }
    }

    @Override
    public boolean add(Utente utente) {
        Ristorante r = chiediRistorante();
        if (r == null) {
            System.out.println("Operazione annullata.");
            return false;
        }

        for (AssGestoreRistoranti ass : assGestoreRistorantiServ.get().values()) {
            if (ass.getRistorantiList().contains(r)) {
                System.out.println(" Il ristorante \"" + r.getNome() + "\" è già stato assegnato a un altro gestore.");
                return false;
            }
        }
        List<Ristorante> ristorantiList = new ArrayList<>();
        ristorantiList.add(r);
        AssGestoreRistoranti assGestoreRistoranti = new AssGestoreRistoranti(utente.getUsername(), ristorantiList);
        boolean b = assGestoreRistorantiServ.add(assGestoreRistoranti);
        if (b) {
            System.out.println("Il ristorante \"" + r.getNome() + "\" è stato aggiunto alla tua lista.");
        }
        return b;
    }

    @Override
    public AssGestoreRistoranti get(Utente valore) {
        return assGestoreRistorantiServ.get(valore.getUsername());
    }

    @Override
    public boolean remove(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean put(Utente valore) {
        Ristorante r = chiediRistorante();

        for (AssGestoreRistoranti ass : assGestoreRistorantiServ.get().values()) {
            if (!ass.getUsernameRistoratore().equals(valore.getUsername())
                    && ass.getRistorantiList().contains(r)) {
                System.out.println("️ Il ristorante \"" + r.getNome() + "\" è già stato assegnato a un altro gestore.");
                return false;
            }
        }
        List<Ristorante> ristorantiList = assGestoreRistorantiServ.get(valore.getUsername()).getRistorantiList();
        if (ristorantiList.contains(r)) {
            ristorantiList.add(r);

            AssGestoreRistoranti assGestoreRistoranti = new AssGestoreRistoranti(valore.getUsername(), ristorantiList);
            boolean b = assGestoreRistorantiServ.put(assGestoreRistoranti.getUsernameRistoratore(), assGestoreRistoranti);
            if (b) {
                System.out.println("Il ristorante \"" + r.getNome() + "\" è stato aggiunto alla tua lista.");
                return false;
            }
            return b;
        } else {
            System.out.println("ℹ️ Il ristorante \"" + r.getNome() + "\" è già nella tua lista.");
            return false;
        }
    }

    @Override
    public void visualizza() {
        System.out.println("Ristoranti associati:");
        for (AssGestoreRistoranti ass : assGestoreRistorantiServ.get().values()) {
            System.out.println("Gestore: " + ass.getUsernameRistoratore());
            for (Ristorante r : ass.getRistorantiList()) {
                System.out.println("- " + r.getNome());
            }
        }
    }

    @Override
    public void visualizza(AssGestoreRistoranti valore) {
        System.out.println("Gestore: " + valore.getUsernameRistoratore());
        for (Ristorante r : valore.getRistorantiList()) {
            System.out.println("- " + r.getNome());
        }
    }

    @Override
    public void visualizza(Utente chiave) {
        AssGestoreRistoranti ass = assGestoreRistorantiServ.get(chiave.getUsername());
        if (ass != null) {
            visualizza(ass);
        } else {
            System.out.println("Nessun ristorante trovato per questo gestore.");
        }
    }
}
