/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.Recensione;
import entita.Utente;
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
public class RecensioneUI {

    Scanner scanner;
    RecensioneService RS;
    RecensioneService recServ = new RecensioneService();
    RistoranteService ristoranteServ = new RistoranteService();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RecensioneUI(Scanner scanner, RecensioneService RS) {
        this.scanner = scanner;
        this.RS = RS;
    }

    public void visualizzaUtente(Utente utente) {
        HashMap<Integer, Recensione> recensioniMap = recServ.getRecensioniHashMap();
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

    private Recensione modificaInformazioni(Recensione recensione) {
        System.out.println("Modifica la tua recensione");
        LocalDate data = LocalDate.now();
        System.out.println("Scegli il numero di stelle (1-5): ");
        short stelle = scanner.nextShort();
        recensione.setStelle(stelle);
        System.out.println("Modifica il testo: ");
        String testo = scanner.next();
        recensione.setTesto(testo);
        return recensione;
    }

    public void add(Utente utente) {
        System.out.println("Recensione ristorante");
        String nomeRistorante;
        do {
            System.out.println("Inserire il nome del ristorante: ");
            nomeRistorante = scanner.next();
        } while (!ristoranteServ.containsKey(nomeRistorante));
        short nStelle;
        do {
            System.out.println("Inserire il numero di stelle (1-5):");
            nStelle = scanner.nextShort();
        } while (nStelle > 5 || nStelle < 1);
        System.out.println("Inserire il testo");
        String testo = scanner.next();
        Recensione r = new Recensione(-1, utente.getUsername(), nStelle, testo, LocalDate.parse(LocalDate.now().format(formatter)), nomeRistorante);
        recServ.add(r);
    }

    public void put(Utente utente) {
        visualizzaUtente(utente);
        Recensione recensioneNuova = new Recensione();
        System.out.println("Scegliere la recensione da modificare (ID): ");
        Integer sceltaID = scanner.nextInt();
        recensioneNuova = modificaInformazioni(recServ.getRecensioniHashMap().get(sceltaID));
        recServ.put(sceltaID, recensioneNuova);
    }

    public void remove(Utente utente) {
        visualizzaUtente(utente);
        System.out.println("Scegliere la recensione da eliminare (ID): ");
        Integer sceltaID = scanner.nextInt();
        recServ.remove(sceltaID);
    }
}
