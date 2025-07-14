/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.associazioni.AssGestoreRistoranti;
import entita.associazioni.Recensione;
import entita.associazioni.RispostaRecensioni;
import entita.dominio.Gestore;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import repository.AssGestoreRistorantiService;
import repository.RecensioneService;
import repository.RispostaRecensioniService;
import java.util.NoSuchElementException;

/**
 *
 * @author armuh
 */
public class RispostaRecensioniUI implements ComandiUI<Gestore, RispostaRecensioni> {

    Scanner scanner;
    RispostaRecensioniService rispostaRecensioniServ;
    RecensioneService recensioneServ;
    AssGestoreRistorantiService assGestoreRistorantiServ;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RispostaRecensioniUI(Scanner scanner, RispostaRecensioniService rispostaRecensioniServ,
            RecensioneService recensioneServ, AssGestoreRistorantiService assGestoreRistorantiServ) {
        this.scanner = scanner;
        this.rispostaRecensioniServ = rispostaRecensioniServ;
        this.recensioneServ = recensioneServ;
        this.assGestoreRistorantiServ = assGestoreRistorantiServ;
    }

    private RispostaRecensioni chiedi(Gestore utente) {
        int idRif;
        String username = utente.getUsername();
        String testo;
        LocalDate data;

        HashMap<Integer, Recensione> recensioniMap = recensioneServ.get();
        List<AssGestoreRistoranti> assGestoreRistorantiList = assGestoreRistorantiServ.get(username);
        if (assGestoreRistorantiList == null) {
            System.out.println("Nessun ristorante associato al gestore.");
            return null;
        }
        HashMap<Integer, String> ristorantiPossedutiRecensiti = new HashMap<>();
        for (AssGestoreRistoranti assGestoreRistoranti : assGestoreRistorantiList) {
            for (Recensione recensione : recensioniMap.values()) {
                if ((recensione.getRistoranteRecensito().compareTo(assGestoreRistoranti.getRistorantePosseduto())) == 0) {
                    ristorantiPossedutiRecensiti.put(recensione.getID(), assGestoreRistoranti.getRistorantePosseduto());
                }
            }
        }
        if (!ristorantiPossedutiRecensiti.isEmpty()) {
            for (Integer  i: ristorantiPossedutiRecensiti.keySet()) {
                System.out.println("ID: " + i);
                System.out.println("Nome: " + ristorantiPossedutiRecensiti.get(i));
                System.out.println("Recensione: " + recensioniMap.get(i).getTesto());
            }
            int scelta = -1;
            do {
                System.out.println("Scegliere uno tra questi ristoranti (ID) oppure 0 per annullare: ");
                try {
                    scelta = scanner.nextInt();
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserire un numero.");
                    scanner.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Input interrotto. Operazione annullata.");
                    return null;
                }
                scanner.nextLine();
                if (scelta == 0) {
                    System.out.println("Operazione annullata.");
                    return null;  // User decided to cancel
                }
                if (!ristorantiPossedutiRecensiti.containsKey(scelta)) {
                    System.out.println("ID non valido. Riprova.");
                }
            } while (!ristorantiPossedutiRecensiti.containsKey(scelta));
            idRif = scelta;
            System.out.println("Scrivere una risposta: ");
            try {
                testo = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Input interrotto. Operazione annullata.");
                return null;
            }
            data = LocalDate.now();
            return new RispostaRecensioni(-1, idRif, username, testo, data);
        } else {
            System.out.println("Nessun ristorante in suo possesso è stato recensito");
            return null;
        }
    }

    @Override
    public boolean add(Gestore utente) {
        RispostaRecensioni rispostaRecensioni = chiedi(utente);
        if (rispostaRecensioni != null) {
            return rispostaRecensioniServ.add(rispostaRecensioni);
        } else {
            return false;
        }
    }

    @Override
    public RispostaRecensioni get(Gestore utente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(Gestore utente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean put(Gestore utente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Gestore utente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(RispostaRecensioni valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
