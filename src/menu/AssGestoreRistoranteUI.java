/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.associazioni.AssGestoreRistoranti;
import entita.dominio.Ristorante;
import entita.dominio.Utente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import repository.AssGestoreRistorantiService;
import repository.RistoranteService;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranteUI implements ComandiUI<Utente, AssGestoreRistoranti> {

    AssGestoreRistorantiService assGestoreRistorantiServ;
    Scanner scanner;
    RistoranteService ristoranteServ;

    public AssGestoreRistoranteUI(Scanner scanner, AssGestoreRistorantiService assGestoreRistorantiServ, RistoranteService ristoranteServ) {
        this.scanner = scanner;
        this.assGestoreRistorantiServ = assGestoreRistorantiServ;
        this.ristoranteServ = ristoranteServ;
    }

    public Ristorante chiediRistorante() {
        String nomeRistorante;
        do {
            System.out.println("Scrivere il nome del ristorante: ");
            nomeRistorante = scanner.next();
        } while (!ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);
    }

    public void aggiungi(Utente utente) {
        if (get(utente) != null) {
            put(utente);
        } else {
            add(utente);
        }
    }

    @Override
    public boolean add(Utente utente) {
        Ristorante r = chiediRistorante();
        List<Ristorante> ristorantiList = new ArrayList<>();
        ristorantiList.add(r);
        AssGestoreRistoranti assGestoreRistoranti = new AssGestoreRistoranti(utente.getUsername(), ristorantiList);
        boolean b = assGestoreRistorantiServ.add(assGestoreRistoranti);
        return b;
    }

    @Override
    public AssGestoreRistoranti get(Utente valore) {
        return assGestoreRistorantiServ.get(valore.getUsername());
    }

    @Override
    public AssGestoreRistoranti remove(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AssGestoreRistoranti put(Utente valore) {
        Ristorante r = chiediRistorante();
        List<Ristorante> ristorantiList = new ArrayList<>();
        ristorantiList.add(r);
        AssGestoreRistoranti assGestoreRistoranti = new AssGestoreRistoranti(valore.getUsername(), ristorantiList);
        AssGestoreRistoranti b = assGestoreRistorantiServ.put(assGestoreRistoranti);
        return b;
    }

    @Override
    public void visualizza() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(AssGestoreRistoranti valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Utente chiave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
