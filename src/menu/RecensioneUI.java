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
public class RecensioneUI implements ComandiUI<Utente, Recensione> {

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
        System.out.println("Modifica la tua recensione");
        LocalDate data = LocalDate.now();
        System.out.println("Scegli il numero di stelle (1-5): ");
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
        Recensione recensioneNuova = new Recensione();
        System.out.println("Scegliere la recensione da modificare (ID): ");
        int sceltaID;
        try {
            sceltaID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("ID non valido. Operazione annullata.");
            return false;
        }

        recensioneNuova = modificaInformazioni(recServ.get().get(sceltaID));
        return recServ.put(recensioneNuova.getID(), recensioneNuova);
    }

    @Override
    public boolean remove(Utente utente) {
        visualizza(utente);
        System.out.println("Scegliere la recensione da eliminare (ID): ");
        int sceltaID;

        try {
            sceltaID = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("ID non valido. Operazione annullata.");
            return false;
        }

        return recServ.remove(sceltaID);
    }

    public void mediaStelle(Gestore gestore) {
        HashMap<Ristorante, Float> mediaStelle = recServ.mediaStelle(gestore);
        System.out.println("Ristorante\tMedia stelle");

        float sommaTot = 0;
        int count = 0;

        for (Ristorante ristorante : mediaStelle.keySet()) {
            Float media = mediaStelle.get(ristorante);
            System.out.println(ristorante.getNome() + "\t\t\t" + media);
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

    @Override
    public Recensione get(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza() {
        HashMap<Integer, Recensione> recensioniMap = recServ.get();
        List<Recensione> recensioniList = new ArrayList<>();
        String[] intestazione = {"ID", "username", "stelle",
            "data", "ristorante", "testo",};
        StringBuilder tabella = new StringBuilder();
        tabella.append(String.format("%-5s %-10s %-10s %-30s %-10s%n",
                intestazione[0], intestazione[2],
                intestazione[3], intestazione[4], intestazione[5]));
        for (Recensione recensione : recensioniMap.values()) {
            recensioniList.add(recensione);
        }

        for (Recensione recensione : recensioniList) {
            tabella.append(String.format("%-5s %-10s %-10s %-30s %-10s%n",
                    recensione.getID(), recensione.getStelle(),
                    recensione.getData(), recensione.getRistoranteRecensito(), recensione.getTesto()));
        }
        System.out.println(tabella.toString());
    }

    @Override
    public void visualizza(Utente utente) {
        HashMap<Integer, Recensione> recensioniMap = recServ.get();
        List<Recensione> recensioniList = new ArrayList<>();
        String[] intestazione = {"ID", "username", "stelle",
            "data", "ristorante", "testo",};
        StringBuilder tabella = new StringBuilder();
        tabella.append(String.format("%-5s %-10s %-10s %-30s %-10s%n",
                intestazione[0], intestazione[2],
                intestazione[3], intestazione[4], intestazione[5]));
        for (Recensione recensione : recensioniMap.values()) {
            if (recensione.getUsername().equals(utente.getUsername())) {
                recensioniList.add(recensione);
            }
        }

        for (Recensione recensione : recensioniList) {
            tabella.append(String.format("%-5s %-10s %-10s %-30s %-10s%n",
                    recensione.getID(), recensione.getStelle(),
                    recensione.getData(), recensione.getRistoranteRecensito(), recensione.getTesto()));
        }
        System.out.println(tabella.toString());

    }

    @Override
    public void visualizza(Recensione valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
