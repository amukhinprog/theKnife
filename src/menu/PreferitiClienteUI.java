/**Mukhin Artur 760942 CO
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
public class PreferitiClienteUI implements ComandiUI<Utente, PreferitiCliente> {

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
        try{
            scanner.nextLine();
            do {
            System.out.println("Inserire il nome del ristorante: ");
            nomeRistorante = scanner.nextLine();
               if (!ristoranteServ.containsKey(nomeRistorante)){
                System.out.println("Ristorante non trovato. Riprova.");
               }
            } while (!ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);

    } catch (NoSuchElementException | IllegalStateException e) {
        System.out.println("Interruzione rilevata o input non valido. Operazione annullata.");
        return null; 
    }
}

    public void aggiungi(Utente utente) {
        if (get(utente) != null) {
            put(utente);
        } else {
            add(utente);
        }
    }

    @Override
    public boolean add(Utente valore) {
        Ristorante r = chiediRistorante();
        if (r == null) return false;
        List<Ristorante> ristorantiList = new ArrayList<>();
        ristorantiList.add(r);
        PreferitiCliente preferitiCliente = new PreferitiCliente(valore.getUsername(), ristorantiList);
        return PCS.add(preferitiCliente);
    }

    @Override
    public boolean remove(Utente utente) {
        Ristorante ristorante = chiediRistorante();
        if (ristorante == null) return false;
        PreferitiCliente preferitiCliente = get(utente);
        List<Ristorante> ristorantiList = preferitiCliente.getRistorantiPreferiti();
        ristorantiList.remove(ristorante);
        String username = preferitiCliente.getUsernameCliente();
        return PCS.put(username, preferitiCliente);
    }

    @Override
    public PreferitiCliente get(Utente valore) {
        return PCS.get(valore.getUsername());
    }

    @Override
    public boolean put(Utente valore) {
        Ristorante r = chiediRistorante();
        if (r == null) return false;
        List<Ristorante> ristorantiListValore = PCS.get(valore.getUsername()).getRistorantiPreferiti();
        if (!ristorantiListValore.contains(r)) {
            ristorantiListValore.add(r);
        }
        PreferitiCliente preferitiCliente = new PreferitiCliente(valore.getUsername(), ristorantiListValore);
        return PCS.put(valore.getUsername(), preferitiCliente);
    }

    @Override
    public void visualizza() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Utente valore) {
        String username = valore.getUsername();
        if (PCS.get().containsKey(username)) {
            List<Ristorante> listaRistoranti = PCS.get().get(username).getRistorantiPreferiti();
            for (Ristorante r : listaRistoranti) {
                System.out.println("Nome: " + r.getNome());
                System.out.println("Locazione: " + r.getLocazione());
                System.out.println("Prezzo: " + r.getPrezzo() + " euro");
                System.out.println("Tipo cucina: " + r.getCucina());
                System.out.println("Servizio delivery: " + r.isDelivery());
                System.out.println("Servizio prenotazione: " + r.isPrenotazione() + "\n\n");
            }

        }
    }

    @Override
    public void visualizza(PreferitiCliente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
