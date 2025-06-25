/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.Ristorante;
import entita.Utente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import repository.PreferitiClienteService;
import repository.RistoranteService;

/**
 *
 * @author armuh
 */
public class PreferitiClienteUI {

    Scanner scanner;
    PreferitiClienteService PCS;
    RistoranteService ristoranteServ;

    public PreferitiClienteUI(Scanner scanner, PreferitiClienteService PCS, RistoranteService ristoranteServ) {
        this.PCS = PCS;
        this.scanner = scanner;
        this.ristoranteServ = ristoranteServ;
    }

    public void visualizza(String username) {
        PreferitiClienteService PCS = new PreferitiClienteService();
        if (PCS.getPreferitiMap().containsKey(username)) {
            List<Ristorante> listaRistoranti = PCS.getPreferitiMap().get(username).getRistorantiPreferiti();
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

    private Ristorante chiediRistorante() {
        System.out.println("Inserire il nome del ristorante: ");
        String nomeRistorante;
        do {
            nomeRistorante = scanner.next();
        } while (!ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);
    }

    public void add(Utente utente) {
        Ristorante r = chiediRistorante();
        PCS.add(utente, r);
    }

    public void remove(Utente utente) {
        Ristorante r = chiediRistorante();
        PCS.remove(utente, r);
    }
}
