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
        short stelle = scanner.nextShort();
        scanner.nextLine();
        recensione.setStelle(stelle);
        System.out.println("Modifica il testo: ");
        String testo = scanner.nextLine();
        recensione.setTesto(testo);
        return recensione;
    }

    @Override
    public boolean add(Utente utente) {
        System.out.println("Recensione ristorante");
        String nomeRistorante;
        do {
            System.out.println("Inserire il nome del ristorante: ");
            nomeRistorante = scanner.nextLine();
        } while (!ristoranteServ.containsKey(nomeRistorante));
        short nStelle;
        do {
            System.out.println("Inserire il numero di stelle (1-5):");
            nStelle = scanner.nextShort();
            scanner.nextLine();
        } while (nStelle > 5 || nStelle < 1);
        System.out.println("Inserire il testo");
        String testo = scanner.nextLine();
        Recensione r = new Recensione(-1, utente.getUsername(), nStelle, testo, LocalDate.now(), nomeRistorante);
        return recServ.add(r);
    }

    @Override
    public boolean put(Utente utente) {
        visualizza(utente);
        Recensione recensioneNuova = new Recensione();
        System.out.println("Scegliere la recensione da modificare (ID): ");
        Integer sceltaID = scanner.nextInt();
        recensioneNuova = modificaInformazioni(recServ.get().get(sceltaID));
        return recServ.put(recensioneNuova.getID(), recensioneNuova);
    }

    @Override
    public boolean remove(Utente utente) {
        visualizza(utente);
        System.out.println("Scegliere la recensione da eliminare (ID): ");
        Integer sceltaID = scanner.nextInt();
        Recensione recensione = recServ.get(sceltaID);
        return recServ.remove(sceltaID, recensione);
    }

    public void mediaStelle(Gestore gestore) {
        HashMap<Ristorante, Float> mediaStelle = recServ.mediaStelle(gestore);
        System.out.println("Ristorante\tMedia stelle");
        float sommaTot = 0;
        int count = 0;
        for (Ristorante ristoranteChiave : mediaStelle.keySet()) {
            System.out.println(ristoranteChiave.getNome() + "\t" + mediaStelle.get(ristoranteChiave));
            count++;
            sommaTot += mediaStelle.get(ristoranteChiave);
        }
        float mediaTot = 0;
        try {
            mediaTot = sommaTot / count;
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Media totale: " + mediaTot);
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
