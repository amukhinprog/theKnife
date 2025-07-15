/** Mukhin Artur 760942 CO
 * De Giorgi Filippo 762388 CO
 * Magrin Nicolò 752721 CO
 * Caredda Anna Eleonora 762576 CO
 */
package menu;

import entita.dominio.Gestore;
import entita.associazioni.Recensione;
import entita.dominio.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import repository.RecensioneService;
import repository.RistoranteService;
import java.util.NoSuchElementException;

/**
 *
 * @author armuh
 */
public class RecensioneUI implements ComandiUI<Utente, List<Recensione>> {

    Scanner scanner;
    RecensioneService recServ;
    RistoranteService ristoranteServ;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RecensioneUI(Scanner scanner, RecensioneService recensioneServ, RistoranteService ristoranteServ) {
        this.scanner = scanner;
        this.recServ = recensioneServ;
        this.ristoranteServ = ristoranteServ;
    }

    private Recensione modificaInformazioni(Recensione recensione) {
        scanner.nextLine();
        System.out.println("Modifica la tua recensione");
        LocalDate data = LocalDate.now();
        short stelle;

        while (true) {
            try {
                System.out.print("Scegli il numero di stelle (1-5): ");
                stelle = Short.parseShort(scanner.nextLine());
                if (stelle == 0) {
                    System.out.println("Operazione annullata.");
                    return null;
                }
                if (stelle >= 1 && stelle <= 5) {
                    break;
                }
                System.out.println("Valore non valido. Inserisci un numero tra 1 e 5.");
            } catch (NumberFormatException | NoSuchElementException e) {
                System.out.println("Input non valido. Operazione annullata.");
                return null;
            }
        }

        recensione.setStelle(stelle);

        System.out.print("Modifica il testo (o 0 per annullare): ");
        try {
            String testo = scanner.nextLine();
            if (testo.equals("0")) {
                System.out.println("Operazione annullata.");
                return null;  // Annulla l'operazione
            }
            recensione.setTesto(testo);
        } catch (NoSuchElementException e) {
            System.out.println("Input interrotto. Operazione annullata.");
            return null;
        }

        return recensione;
    }

    @Override
    public boolean add(Utente utente) {
        System.out.println("Recensione ristorante");
        String nomeRistorante;

        try {
            do {
                scanner.nextLine();
                System.out.print("Inserire il nome del ristorante (o 0 per annullare): ");
                nomeRistorante = scanner.nextLine();
                if (nomeRistorante.equals("0")) {
                    System.out.println("Operazione annullata.");
                    return false;
                }
                if (!ristoranteServ.containsKey(nomeRistorante)) {
                    System.out.println("Ristorante non trovato. Riprova.");
                }
            } while (!ristoranteServ.containsKey(nomeRistorante));
        } catch (NoSuchElementException e) {
            System.out.println("Input interrotto. Operazione annullata.");
            return false;
        }
        short nStelle;
        do {
            System.out.println("Inserire il numero di stelle (1-5) (o 0 per annullare):");
            nStelle = scanner.nextShort();
            scanner.nextLine();
            if (nStelle == 0) {
                System.out.println("Operazione annullata.");
                return false;
            }
        } while (nStelle > 5 || nStelle < 1);
        System.out.println("Inserire il testo (o 0 per annullare):");
        String testo;
        try {
            testo = scanner.nextLine();
            if (testo.equals("0")) {
                System.out.println("Operazione annullata.");
                return false;  // Annulla l'operazione
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input interrotto. Operazione annullata.");
            return false;
        }

        Recensione r = new Recensione(-1, utente.getUsername(), nStelle, testo, LocalDate.now(), nomeRistorante);
        return recServ.add(r);
    }

    @Override
    public boolean put(Utente utente) {
        visualizza(utente);
        Recensione recensioneNuova;
        System.out.println("Scegliere la recensione da modificare (ID): ");
        int sceltaID;
        scanner.nextLine();
        try {
            sceltaID = scanner.nextInt();
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("ID non valido. Operazione annullata.");
            return false;
        }
        Recensione recensioneVecchia = new Recensione (recServ.get().get(sceltaID));
        recensioneNuova = modificaInformazioni(recensioneVecchia);
        return recServ.put(recensioneNuova.getID(), recensioneNuova);
    }

    @Override
    public boolean remove(Utente utente) {
        visualizza(utente);
        System.out.println("Scegliere la recensione da eliminare (ID): ");
        int sceltaID;
        scanner.nextLine();
        try {
            sceltaID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("ID non valido. Operazione annullata.");
            return false;
        }
        Recensione recensione = recServ.get(sceltaID);
        return recServ.remove(sceltaID, recensione);
    }

    public void mediaStelle(Gestore gestore) {
        HashMap<String, Float> mediaStelle = recServ.mediaStelle(gestore);
        System.out.println("Ristorante\tMedia stelle");

        float sommaTot = 0;
        int count = 0;

        for (String nomeRistorante : mediaStelle.keySet()) {
            Float media = mediaStelle.get(nomeRistorante);
            System.out.println(nomeRistorante + "\t\t\t" + media);
            sommaTot += media;
            count++;
        }

        if (count > 0) {
            float mediaTot = sommaTot / count;
            System.out.printf("Media totale: %.2f%n", mediaTot);
        } else {
            System.out.println("Nessuna recensione disponibile.");
        }
    }

    public void numeroRecensioni(Gestore gestore) {
        System.out.println("Numero di recensioni: " + recServ.getNumeroRecensioni(gestore));
    }

    public List<Recensione> get(String nomeRistorante) {
        List<Recensione> recensioneList = recServ.get(nomeRistorante);
        visualizza(recensioneList);
        return recensioneList;
    }

    @Override
    public List<Recensione> get(Utente valore) {
        HashMap<Integer, Recensione> recensioniMap = recServ.get();
        List<Recensione> recensioniList = new ArrayList<>();
        for (Recensione recensione : recensioniMap.values()) {
            if (recensione.getUsername().equals(valore.getUsername())) {
                recensioniList.add(recensione);
            }
        }
        return recensioniList;
    }

    @Override
    public void visualizza() {
        HashMap<Integer, Recensione> recensioniMap = recServ.get();
        List<Recensione> recensioniList = new ArrayList<>();
        for (Recensione recensione : recensioniMap.values()) {
            recensioniList.add(recensione);
        }
        visualizza(recensioniList);
    }

    @Override
    public void visualizza(Utente utente) {
        List<Recensione> recensioniList = get(utente);
        visualizza(recensioniList);
    }

    @Override
    public void visualizza(List<Recensione> valori) {
        String[] intestazione = {"ID", "username", "stelle",
            "data", "ristorante", "testo",};
        StringBuilder tabella = new StringBuilder();
        tabella.append(String.format("%-5s %-10s %-10s %-30s %-10s%n",
                intestazione[0], intestazione[2],
                intestazione[3], intestazione[4], intestazione[5]));

        for (Recensione recensione : valori) {
            tabella.append(String.format("%-5s %-10s %-10s %-30s %-10s%n",
                    recensione.getID(), recensione.getStelle(),
                    recensione.getData(), recensione.getRistoranteRecensito(), recensione.getTesto()));
        }
        System.out.println(tabella.toString());
    }
}
