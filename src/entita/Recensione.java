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

    private int ID;
    private String username;
    private short stelle;
    private String testo;
    private LocalDate data;
    String ristoranteRecensito;

    public Recensione(int ID, String username, short stelle, String testo, LocalDate data, String ristoranteRecensito) {
        this.ID = ID;
        this.username = username;
        this.stelle = stelle;
        this.testo = testo;
        this.data = data;
        this.ristoranteRecensito = ristoranteRecensito;
    }

    public Recensione() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public short getStelle() {
        return stelle;
    }

    public String getTesto() {
        return testo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getRistoranteRecensito() {
        return ristoranteRecensito;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStelle(short stelle) {
        this.stelle = stelle;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setRistoranteRecensito(String ristoranteRecensito) {
        this.ristoranteRecensito = ristoranteRecensito;
    }
    public void setTesto(String testo){
        this.testo = testo;
    }
}
