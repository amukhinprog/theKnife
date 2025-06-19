/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entita;

/**
 *
 * @author Utente
 */
import gestioneFile.FileRecensioni;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import repository.RistoranteService;

public class Recensione {

    private static HashMap<Integer, Recensione> recensioniHashMap = new FileRecensioni().ottieniHashMap();
    private int ID;
    private String utente;
    private short stelle;
    private String testo;
    private LocalDate data;
    List<String> recensioni = new ArrayList<>();
    String ristoranteRecensito;
    private RistoranteService ristoranteServ = new RistoranteService(); 
    public Recensione(int ID, String utente, short stelle, String testo, LocalDate data, String ristoranteRecensito) {
        this.ID = ID;
        this.stelle = stelle;
        this.testo = testo;
        this.data = data;
        this.ristoranteRecensito = ristoranteRecensito;
    }

    public static HashMap<Integer, Recensione> getRecensioniHashMap() {
        return recensioniHashMap;
    }

    public static void setRecensioniHashMap(HashMap<Integer, Recensione> recensioniHashMap) {
        Recensione.recensioniHashMap = recensioniHashMap;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getID() {
        return ID;
    }

    public short getstelle() {
        return stelle;
    }

    public String gettesto() {
        return testo;
    }

    public LocalDate getdata() {
        return data;
    }

    public String getristoranteRecensito() {
        return ristoranteRecensito;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setstelle(short stelle) {
        this.stelle = stelle;
    }

    public void setdata(LocalDate data) {
        this.data = data;
    }

    public void setristoranteRecensito(String ristoranteRecensito) {
        this.ristoranteRecensito = ristoranteRecensito;
    }

    public void aggiungiRecensione(Utente utente) {
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
