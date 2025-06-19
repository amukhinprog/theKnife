/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.AssGestoreRistoranti;
import entita.Gestore;
import entita.Ristorante;
import static entita.Ristorante.ristoranteVuoto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import repository.RistoranteService;

/**
 *
 * @author armuh
 */
public class RistoranteUI {

    private Scanner scanner;
    private RistoranteService ristoranteServ = new RistoranteService();

    public RistoranteUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public Ristorante chiediInformazioniRistorante() {
        Scanner scanner = new Scanner(System.in);
        String nomeRistorante;
        do {
            System.out.println("Scrivere il nome del ristorante: ");
            nomeRistorante = scanner.next();
        } while (ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);
    }

    private Ristorante inserisciNuovoRistorante() {
        String nome;
        do {
            System.out.println("Nome: ");
            nome = scanner.next();
        } while (ristoranteServ.containsKey(nome));

        System.out.println("Indirizzo: ");
        String indirizzo = scanner.next();

        System.out.println("Locazione: ");
        String locazione = scanner.next();

        System.out.println("Prezzo: ");
        float prezzo = scanner.nextFloat();

        System.out.println("Cucina:");
        String cucina = scanner.next();

        System.out.println("Longitudine: ");
        float longitudine = scanner.nextFloat();

        System.out.println("Latitudine: ");
        float latitudine = scanner.nextFloat();

        System.out.println("Numero di telefono: ");
        String numeroTelefono = scanner.next();

        System.out.println("Delivery: ");
        boolean delivery = scanner.nextBoolean();

        System.out.println("Url:");
        String url = scanner.next();

        System.out.println("Website Url:");
        String webSiteUrl = scanner.next();

        System.out.println("Prenotazione: ");
        boolean prenotazione = scanner.nextBoolean();

        System.out.println("Descrizione: ");
        String descrizione = scanner.next();

//        System.out.println("Stelle: ");
//        short stelle = scanner.nextShort();

        return new Ristorante(nome, indirizzo, locazione, prezzo, cucina, longitudine, latitudine, numeroTelefono, delivery, url, webSiteUrl, prenotazione, descrizione/*, stelle*/);
    }

    
    public void visualizzaRistorante(List<Ristorante> ristoranti) {
//        System.out.println(ristoranti);

        for (Ristorante ristorante : ristoranti) {
            System.out.println("Locazione: " + ristorante.getLocazione());
            System.out.println("Prezzo: " + ristorante.getPrezzo() + " euro");
            System.out.println("Tipo cucina: " + ristorante.getCucina());
            System.out.println("Servizio delivery: " + ristorante.isDelivery());
            System.out.println("Servizio prenotazione: " + ristorante.isPrenotazione() + "\n\n");
            //System.out.println("Stelle: " + ristorante.getStelle());
        }
    }

    public Ristorante inserisciGenericoRistorante(Gestore utente) {
        char scelta = ' ';
        Ristorante r = null;
        do {
            System.out.println("Vuole inserire un ristorante di nuova apertura? (s/n)");
            System.out.println("0. Esci");
            scelta = scanner.next().charAt(0);
            switch (scelta) {
                case 's':
                    r = inserisciNuovoRistorante();
                    break;
                case 'n':
                    r = inserisciRistoranteEsistente(utente);
                    break;
                default:
                    System.out.println("Riprovare");
                    break;
            }
        } while (scelta != '0');
        return r;
    }

    public Ristorante inserisciRistoranteEsistente(Gestore utente) {
        String nomeRistorante;

        while (true) {
            nomeRistorante = chiediNomeRistorante(scanner);

            if (nomeRistorante.equals("0")) {
                return ristoranteVuoto();
            }

            if (!ristoranteServ.containsKey(nomeRistorante)) {
                System.out.println("Ristorante non trovato.");
                continue;
            }

            Ristorante ristorante = ristoranteServ.get(nomeRistorante);

            if (ristoranteServ.ristoranteGiaPossedutoDalGestore(utente, ristorante)) {
                System.out.println("Il ristorante è già presente nel vostro elenco.");
                continue;
            }

            if (ristoranteServ.ristoranteHaAltroProprietario(utente, ristorante)) {
                System.out.println("Il ristorante inserito ha già un proprietario.");
                continue;
            }

            return ristorante;
        }
    }

    private String chiediNomeRistorante(Scanner scanner) {
        System.out.println("Inserire il nome del ristorante: ");
        System.out.println("0. Esci");
        return scanner.next();
    }

    
}
