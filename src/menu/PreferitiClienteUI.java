/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.PreferitiCliente;
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
public class PreferitiClienteUI implements ComandiUI<Utente> {

    Scanner scanner;
    PreferitiClienteService PCS;
    RistoranteService ristoranteServ;

    public PreferitiClienteUI(Scanner scanner, PreferitiClienteService PCS, RistoranteService ristoranteServ) {
        this.PCS = PCS;
        this.scanner = scanner;
        this.ristoranteServ = ristoranteServ;
    }

    private Ristorante chiediRistorante() {
        System.out.println("Inserire il nome del ristorante: ");
        String nomeRistorante;
        do {
            nomeRistorante = scanner.next();
        } while (!ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);
    }

//    public void removeSingolo(Utente valore) {
//        Ristorante r = chiediRistorante();
//        PCS.remove(valore, r);
//    }

    @Override
    public boolean add(Utente valore) {
        Ristorante r = chiediRistorante();
        List<Ristorante> ristorantiList = new ArrayList<>();
        ristorantiList.add(r);
        PreferitiCliente preferitiCliente = new PreferitiCliente(valore.getUsername(), ristorantiList);
        return PCS.add(preferitiCliente);
    }

    @Override
    public Utente remove(Utente utente) {
        Ristorante r = chiediRistorante();
        List<Ristorante> ristorantiList = new ArrayList<>();
        ristorantiList.add(r);
        PreferitiCliente preferitiCliente = new PreferitiCliente(utente.getUsername(), ristorantiList);
        String username = preferitiCliente.getUsernameCliente();
        PCS.remove(username);
        return utente;
    }

    @Override
    public Utente get(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Utente put(Utente valore) {
        Ristorante r = chiediRistorante();
        List<Ristorante> ristorantiListValore = PCS.get(valore.getUsername()).getRistorantiPreferiti();
        ristorantiListValore.remove(r);
        PreferitiCliente preferitiCliente = new PreferitiCliente(valore.getUsername(), ristorantiListValore);
        PCS.put(preferitiCliente);
        return valore;
    }

    @Override
    public void visualizza() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Utente valore) {
        PreferitiClienteService PCS = new PreferitiClienteService();
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
}
