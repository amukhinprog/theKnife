/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.Recensione;
import entita.Utente;
import gestioneFile.FileRecensioni;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author armuh
 */
public class RecensioneService {

    private HashMap<Integer, Recensione> recensioniHashMap = new FileRecensioni().ottieniHashMap();
    private RistoranteService ristoranteServ = new RistoranteService();

    public HashMap<Integer, Recensione> getRecensioniHashMap() {
        return recensioniHashMap;
    }

    public void setRecensioniHashMap(HashMap<Integer, Recensione> recensioniHashMap) {
        this.recensioniHashMap = recensioniHashMap;
    }

    public void add(Utente utente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Recensione ristorante");
        String nomeRistorante;
        do {
            System.out.println("Inserire il nome del ristorante: ");
            nomeRistorante = scanner.next();
        } while (ristoranteServ.containsKey(nomeRistorante));
        short nStelle;
        do {
            System.out.println("Inserire il numero di stelle (1-5):");
            nStelle = scanner.nextShort();
        } while (nStelle > 5 || nStelle < 1);
        System.out.println("Inserire il testo");
        String testo = scanner.next();

        Recensione r = new Recensione(1, utente.getUsername(), nStelle, testo, LocalDate.now(), nomeRistorante);
    }
}
