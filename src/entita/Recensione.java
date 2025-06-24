/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entita;

/**
 *
 * @author Nikoro02
 */
import gestioneFile.FileRecensioni;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import repository.RistoranteService;

public class Recensione {

    private int ID;
    private String utente;
    private short stelle;
    private String testo;
    private LocalDate data;
    String ristoranteRecensito;

    public Recensione(int ID, String utente, short stelle, String testo, LocalDate data, String ristoranteRecensito) {
        this.ID = ID;
        this.stelle = stelle;
        this.testo = testo;
        this.data = data;
        this.ristoranteRecensito = ristoranteRecensito;
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

}
