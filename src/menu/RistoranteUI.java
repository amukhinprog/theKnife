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
    private RistoranteService ristoranteServ;

    public RistoranteUI(Scanner scanner, RistoranteService ristoranteServ) {
        this.scanner = scanner;
        this.ristoranteServ = ristoranteServ;
    }

    public Ristorante chiediInformazioni() {
        Scanner scanner = new Scanner(System.in);
        String nomeRistorante;
        do {
            System.out.println("Scrivere il nome del ristorante: ");
            nomeRistorante = scanner.next();
        } while (ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);
    }

    private Ristorante inserisciNuovo() {
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

    public void visualizza(List<Ristorante> ristoranti) {
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

    public Ristorante inserisciGenerico(Gestore utente) {
        char scelta = ' ';
        Ristorante r = null;
        do {
            System.out.println("Vuole inserire un ristorante di nuova apertura? (s/n)");
            System.out.println("0. Esci");
            scelta = scanner.next().charAt(0);
            switch (scelta) {
                case 's':
                    r = inserisciNuovo();
                    break;
                case 'n':
                    r = inserisciEsistente(utente);
                    break;
                default:
                    System.out.println("Riprovare");
                    break;
            }
        } while (scelta != '0');
        return r;
    }

    public Ristorante inserisciEsistente(Gestore utente) {
        String nomeRistorante;

        while (true) {
            nomeRistorante = chiediNome(scanner);

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

    private String chiediNome(Scanner scanner) {
        System.out.println("Inserire il nome del ristorante: ");
        System.out.println("0. Esci");
        return scanner.next();
    }

    public void cerca() {
        int scelta = -1;
        System.out.println("Inserire 1 per cercare un ristorante per locazione");
        System.out.println("Inserire 2 per cercare un ristorante per tipologia di cucina");
        System.out.println("Inserire 3 per cercare un ristorante per fascia di prezzo");
        System.out.println("Inserire 4 per cercare un ristorante in base alla disponibilita' del servizio di delivery");
        System.out.println("Inserire 5 per cercare un ristorante in base alla disponibilita' del servizio di prenotazione online");
        /*System.out.println("Inserire 6 per cercare un ristorante per media del numero di stelle");
        System.out.println("Inserire 7 per cercare un ristorante per combinazione dei precedenti criteri di ricerca")*;*/

        try {
            scelta = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        switch (scelta) {
            case 1:
                cercaPerLocazione();
                break;
            case 2:
                cercaPerCucina();
                break;
            case 3:
                cercaPerPrezzo();
                break;
            case 4:
                cercaPerDelivery();
                break;
            case 5:
                cercaPerPrenotazione();
                break;
            /*case 6:
                cercaPerStelle();
                break;
           /*case 7:
                cercaCriteri();
                break;*/
            default:
                System.out.println("Scegliere l'opzione corretta");
                break;
        }

    }

    private void cercaPerLocazione() {
        System.out.println("Inserire il nome della citta': ");
        String locazione = scanner.next();
        locazione = locazione.toLowerCase();
        /////QKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK
        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String locazioneRistorante = ristorante.getLocazione().replace("\"", "");
            if (locazioneRistorante.toLowerCase().startsWith(locazione.toLowerCase())) {
                ristorantiList.add(ristorante);
            }
        }
        visualizza(ristorantiList);
    }

    private void cercaPerCucina() {
        System.out.println("Inserire la tipologia di cucina del ristorante : ");
        String cucina = scanner.next();
        cucina = cucina.toLowerCase();
        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String cucinaRistorante = ristorante.getCucina().replace("\"", "");
            if (cucinaRistorante.toLowerCase().startsWith(cucina.toLowerCase())) {
                ristorantiList.add(ristorante);
            }
        }
        visualizza(ristorantiList);
    }

    private void cercaPerPrezzo() {
        System.out.println("Visualizzare i ristoranti con un prezzo minore di: ");
        Float prezzoLimite;
        try {
            prezzoLimite = scanner.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("Inserire un numero valido per il prezzo.");
            return;
        }
        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();
        for (Ristorante ristorante : ristorantiColl) {
            String tipologiaRistorante = ristorante.getCucina().replace("\"", "");
            if (ristorante.getPrezzo() < prezzoLimite) {
                ristorantiList.add(ristorante);
            }
        }
        visualizza(ristorantiList);
    }

    private void cercaPerDelivery() {
        boolean delivery;

        while (true) {
            System.out.print("Vuoi visualizzare solo i ristoranti con delivery? (si/no): ");
            String risposta = scanner.next().trim().toLowerCase();

            if (risposta.equals("sì") || risposta.equals("si")) {
                delivery = true;
                break;
            } else if (risposta.equals("no")) {
                delivery = false;
                break;
            } else {
                System.out.println("Risposta non valida. Inserire 'si' o 'no'.");
            }
        }

        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();

        for (Ristorante ristorante : ristorantiColl) {
            if (ristorante.isDelivery() == delivery) {
                ristorantiList.add(ristorante);
            }
        }

        visualizza(ristorantiList);
    }

    private void cercaPerPrenotazione() {
        boolean prenotazione;

        while (true) {
            System.out.print("Vuoi visualizzare solo i ristoranti con prenotazione online? (si/no): ");
            String risposta = scanner.next().trim().toLowerCase();

            if (risposta.equals("sì") || risposta.equals("si")) {
                prenotazione = true;
                break;
            } else if (risposta.equals("no")) {
                prenotazione = false;
                break;
            } else {
                System.out.println("Risposta non valida. Inserire 'si' o 'no'.");
            }
        }

        Collection<Ristorante> ristorantiColl = ristoranteServ.values();
        List<Ristorante> ristorantiList = new ArrayList<>();

        for (Ristorante ristorante : ristorantiColl) {
            if (ristorante.isPrenotazione() == prenotazione) {
                ristorantiList.add(ristorante);
            }
        }

        visualizza(ristorantiList);
    }

    /*private void cercaPerStelle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il numero esatto di stelle (da 1 a 5): ");
        int numeroStelle;

        try {
            numeroStelle = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Inserire un numero intero valido per le stelle da 1 a 5.");
            return;
        }

        Collection<Ristorante> ristorantiColl = ristoranti.values();
        List<Ristorante> ristorantiList = new ArrayList<>();

        for (Ristorante ristorante : ristorantiColl) {
            if (ristorante.getStelle() == numeroStelle) {
                ristorantiList.add(ristorante);
            }
        }

        visualizzaRistorante(ristorantiList);
    }*/
}
