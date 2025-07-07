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
import entita.dominio.Ristorante;
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
        int ID;
        int idRif;
        String username = utente.getUsername();
        String testo;
        LocalDate data;

//        System.out.println(recensioneServ.get());
        HashMap<Integer, Recensione> recensioniMap = recensioneServ.get();
        AssGestoreRistoranti assGestoreRistoranti = assGestoreRistorantiServ.get(username);
        if (assGestoreRistoranti == null) {
            System.out.println("Nessun ristorante associato al gestore.");
            return null;
        }
        List<Ristorante> ristorantiPossedutiList = assGestoreRistoranti.getRistorantiList();
        HashMap<Integer, Ristorante> ristorantiPossedutiRecensiti = new HashMap<>();
        for (Ristorante ristorantePosseduto : ristorantiPossedutiList) {
            for (Recensione recensione : recensioniMap.values()) {
                if ((recensione.getRistoranteRecensito().compareTo(ristorantePosseduto.getNome())) == 0) {
                    ristorantiPossedutiRecensiti.put(recensione.getID(), ristorantePosseduto);
                }
            }
        }
        if (!ristorantiPossedutiRecensiti.isEmpty()) {
            for (Integer i : ristorantiPossedutiRecensiti.keySet()) {
                System.out.println("ID: " + i);
                System.out.println("Nome: " + ristorantiPossedutiRecensiti.get(i).getNome());
                System.out.println("Recensione: " + recensioniMap.get(i).getTesto());
            }
            int scelta = 0;
            while (!ristorantiPossedutiRecensiti.containsKey(scelta) || ristorantiPossedutiRecensiti.isEmpty()) {
                System.out.println("Scegliere uno tra questi ristoranti (ID): ");
                try {
                    scelta = scanner.nextInt();
                    scanner.nextLine();
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido. Inserire un numero.");
                    scanner.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Input interrotto. Operazione annullata.");
                    return null;
                }
            }
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
