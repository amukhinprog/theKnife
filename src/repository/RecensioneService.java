/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entita.Recensione;
import entita.Ristorante;
import entita.Utente;
import gestioneFile.FileRecensioni;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import menu.RecensioneUI;
import menu.RistoranteUI;

/**
 *
 * @author armuh
 */
public class RecensioneService {

    private HashMap<Integer, Recensione> recensioniHashMap = new FileRecensioni().ottieniHashMap();
    private RistoranteService ristoranteServ = new RistoranteService();
    private FileRecensioni fileRecensioni = new FileRecensioni();
    private Scanner scanner = new Scanner(System.in);
    private RistoranteUI ristoranteUI = new RistoranteUI(scanner, ristoranteServ);
//    private RecensioneUI recensioneUI = new RecensioneUI(scanner, this);
    private static int ID = 0;
    public HashMap<Integer, Recensione> getRecensioniHashMap() {
        return new FileRecensioni().ottieniHashMap();
    }

    public void setRecensioniHashMap(HashMap<Integer, Recensione> recensioniHashMap) {
        this.recensioniHashMap = recensioniHashMap;
    }

    public void add(Recensione recensione) {
        recensione.setID(incID());
        fileRecensioni.scrittura(recensione);
    }

    public void put(Integer id, Recensione recensione) {
        recensioniHashMap.put(id, recensione);
        fileRecensioni.sovraScrivi(recensioniHashMap);
    }

    public void remove(Integer id) {
        recensioniHashMap.remove(id);
        fileRecensioni.sovraScrivi(recensioniHashMap);
    }

    public static int getID() {
        return ID;
    }
    public static int incID(){
        return ++ID;
    }
}
