/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.Gestore;
import entita.Ristorante;
import entita.Utente;
import java.util.HashMap;
import java.util.Scanner;
import repository.AssGestoreRistorantiService;
import repository.GestoreService;
import repository.RistoranteService;

/**
 *
 * @author armuh
 */
public class AssGestoreRistoranteUI {

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

    public void add(Utente utente) {
        Ristorante r = chiediRistorante();
        assGestoreRistorantiServ.add(utente, r);
    }
}
