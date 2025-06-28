/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import entita.associazioni.AssGestoreRistoranti;
import entita.associazioni.Recensione;
import entita.associazioni.RispostaRecensioni;
import entita.dominio.Gestore;
import entita.dominio.Ristorante;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import repository.AssGestoreRistorantiService;
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
    AssGestoreRistorantiService assGestoreRistorantiServ;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RispostaRecensioniUI(Scanner scanner, RispostaRecensioniService rispostaRecensioniServ,
            RecensioneService recensioneServ, AssGestoreRistorantiService assGestoreRistorantiServ) {
        this.scanner = scanner;
        this.rispostaRecensioniServ = rispostaRecensioniServ;
        this.recensioneServ = recensioneServ;
        this.assGestoreRistorantiServ = assGestoreRistorantiServ;
    }

    private RispostaRecensioni chiedi(Gestore utente) {
        int ID;
        int idRif;
        String username = utente.getUsername();
        String testo;
        LocalDate data;

//        System.out.println(recensioneServ.get());
        HashMap<Integer, Recensione> recensioniMap = recensioneServ.get();
        AssGestoreRistoranti assGestoreRistoranti = assGestoreRistorantiServ.get(username);
        List<Ristorante> ristorantiPossedutiList = assGestoreRistoranti.getRistorantiList();
        HashMap<Integer, Ristorante> ristorantiPossedutiRecensiti = new HashMap<>();
        for (Ristorante ristorantePosseduto : ristorantiPossedutiList) {
            for (Recensione recensione : recensioniMap.values()) {
                if ((recensione.getRistoranteRecensito().compareTo(ristorantePosseduto.getNome())) == 0) {
                    ristorantiPossedutiRecensiti.put(recensione.getID(), ristorantePosseduto);
                }
            }
        }
        if (!ristorantiPossedutiRecensiti.isEmpty()) {
            System.out.println(ristorantiPossedutiRecensiti.toString());
            int scelta = 0;
            while (!ristorantiPossedutiRecensiti.containsKey(scelta) || ristorantiPossedutiRecensiti.isEmpty()) {
                System.out.println("Scegliere uno tra questi ristoranti (ID): ");
                scelta = scanner.nextInt();
                scanner.nextLine();
            }
            idRif = scelta;
            System.out.println("Scrivere una risposta: ");
            testo = scanner.nextLine();
            data = LocalDate.now();
            return new RispostaRecensioni(-1, idRif, username, testo, data);
        } else {
            return null;
        }
    }

    @Override
    public boolean add(Gestore utente) {
        RispostaRecensioni rispostaRecensioni = chiedi(utente);
        if (rispostaRecensioni != null) {
            return rispostaRecensioniServ.add(rispostaRecensioni);
        } else {
            return false;
        }
    }

    @Override
    public RispostaRecensioni get(Gestore utente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(Gestore utente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean put(Gestore utente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void visualizza(Gestore utente) {
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
