/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.associazioni.RispostaRecensioni;
import entita.dominio.Gestore;
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

    public RispostaRecensioniUI(Scanner scanner, RispostaRecensioniService rispostaRecensioniServ, RecensioneService recensioneServ) {
        this.scanner = scanner;
        this.rispostaRecensioniServ = rispostaRecensioniServ;
        this.recensioneServ = recensioneServ;
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
    public RispostaRecensioni remove(Gestore chiave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public RispostaRecensioni put(Gestore chiave) {
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
