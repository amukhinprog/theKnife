/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.associazioni.PreferitiCliente;
import entita.dominio.Ristorante;
import entita.dominio.Utente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import repository.PreferitiClienteService;
import repository.RistoranteService;
import java.util.NoSuchElementException;

/**
 *
 * @author armuh
 */
public class PreferitiClienteUI implements ComandiUI<Utente, List<PreferitiCliente>> {

    Scanner scanner;
    PreferitiClienteService PCS;
    RistoranteService ristoranteServ;

    public PreferitiClienteUI(Scanner scanner, PreferitiClienteService PCS, RistoranteService ristoranteServ) {
        this.PCS = PCS;
        this.scanner = scanner;
        this.ristoranteServ = ristoranteServ;
    }

    private Ristorante chiediRistorante() {
        String nomeRistorante;
        try {
            scanner.nextLine();
            do {
                System.out.println("Inserire il nome del ristorante: ");
                nomeRistorante = scanner.nextLine();
                if (!ristoranteServ.containsKey(nomeRistorante)) {
                    System.out.println("Ristorante non trovato. Riprova.");
                    return null;
                }
                if (!ristoranteServ.containsKey(nomeRistorante)) {
                    System.out.println("Ristorante non trovato. Riprova.");
                }
            } while (!ristoranteServ.containsKey(nomeRistorante));
            return ristoranteServ.get(nomeRistorante);

        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Interruzione rilevata o input non valido. Operazione annullata.");
            return null;
        }
    }

    @Override
    public boolean add(Utente chiave) {
        Ristorante ristorante = chiediRistorante();
        if (chiave == null) {
            return false;
        }
        PreferitiCliente preferitiCliente = new PreferitiCliente(chiave.getUsername(), ristorante.getNome());
        List<PreferitiCliente> preferitiClienteList = new ArrayList<>();
        preferitiClienteList.add(preferitiCliente);
        return PCS.add(preferitiClienteList);
    }

    @Override
    public boolean remove(Utente chiave) {
        Ristorante ristorante = chiediRistorante();
        if (ristorante == null) {
            return false;
        }
        String username = chiave.getUsername();
        List<PreferitiCliente> preferitiClienteList = get(chiave);
        if (preferitiClienteList == null) {
            System.out.println("Nessun ristorante preferito trovato.");
            return false;
        }
        PreferitiCliente preferitiCliente = new PreferitiCliente(username, ristorante.getNome());
        preferitiClienteList.remove(preferitiCliente);
        return PCS.put(username, preferitiClienteList);
    }

    @Override
    public List<PreferitiCliente> get(Utente chiave) {
        return PCS.get(chiave.getUsername());
    }

    @Override
    public boolean put(Utente chiave) {
        Ristorante ristorante = chiediRistorante();
        List<PreferitiCliente> preferitiClienteList = get(chiave);

        if (ristorante == null || preferitiClienteList == null) {
            return false;
        }
        PreferitiCliente preferitiCliente = new PreferitiCliente(chiave.getUsername(), ristorante.getNome());
        preferitiClienteList.add(preferitiCliente);
        return PCS.put(chiave.getUsername(), preferitiClienteList);
    }

    @Override
    public void visualizza() {
        String[] intestazione = {""};

    }

    @Override
    public void visualizza(List<PreferitiCliente> preferitiClienteList) {
        if (preferitiClienteList != null) {
            for (PreferitiCliente preferitiCliente : preferitiClienteList) {
                Ristorante ristorante = ristoranteServ.get(preferitiCliente.getRistorantePreferito());
                System.out.println("Nome: " + ristorante.getNome());
                System.out.println("Locazione: " + ristorante.getLocazione());
                System.out.println("Prezzo: " + ristorante.getPrezzo() + " euro");
                System.out.println("Tipo cucina: " + ristorante.getCucina());
                System.out.println("Servizio delivery: " + ristorante.isDelivery());
                System.out.println("Servizio prenotazione: " + ristorante.isPrenotazione() + "\n\n");
            }

        } else {
            System.out.println("Nessun ristorante preferito trovato per l'utente " + preferitiClienteList.getFirst().getUsernameCliente());
        }
    }

    @Override
    public void visualizza(Utente chiave) {
        List<PreferitiCliente> preferitiClienteList = get(chiave);
        visualizza(preferitiClienteList);
    }

}
