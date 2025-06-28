/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.associazioni.RispostaRecensioni;
import entita.dominio.Gestore;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import repository.RecensioneService;
import repository.RispostaRecensioniService;

/**
 *
 * @author armuh
 */
public class RispostaRecensioniUI implements ComandiUI<Gestore, RispostaRecensioni> {

    Scanner scanner;
    RispostaRecensioniService rispostaRecensioniServ;
    RecensioneService recensioneServ;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RispostaRecensioniUI(Scanner scanner, RispostaRecensioniService rispostaRecensioniServ, RecensioneService recensioneServ) {
        this.scanner = scanner;
        this.rispostaRecensioniServ = rispostaRecensioniServ;
        this.recensioneServ = recensioneServ;
    }

    private RispostaRecensioni chiedi() {
        int ID;
        int idRif;
        String username;
        String testo;
        LocalDate data;return null;//continuare
    }

    @Override
    public boolean add(Gestore chiave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RispostaRecensioni get(Gestore chiave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(Gestore chiave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean put(Gestore chiave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Gestore chiave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(RispostaRecensioni valore) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
