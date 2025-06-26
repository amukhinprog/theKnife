/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.AssGestoreRistoranti;
import entita.Ristorante;
import entita.Utente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import repository.AssGestoreRistorantiService;
import repository.RistoranteService;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranteUI  implements ComandiUI<Utente> {

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
        } while (ristoranteServ.containsKey(nomeRistorante));
        return ristoranteServ.get(nomeRistorante);
    }

    @Override
    public boolean add(Utente utente) {
        Ristorante r = chiediRistorante();
        List<Ristorante> ristorantiList = new ArrayList<>();
        ristorantiList.add(r);
        AssGestoreRistoranti assGestoreRistoranti= new AssGestoreRistoranti(utente.getUsername(), ristorantiList);
        boolean b = assGestoreRistorantiServ.add(assGestoreRistoranti);
        return b;
    }

    @Override
    public Utente get(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Utente remove(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Utente put(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Utente valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    
    
}
