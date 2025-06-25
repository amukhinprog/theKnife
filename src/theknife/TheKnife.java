package theknife;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import menu.GestioneMenu;
import entita.AssGestoreRistoranti;
import entita.Gestore;
import entita.Ristorante;
import entita.Utente;
import gestioneFile.*;
import java.io.FileNotFoundException;
import gestioneFile.FileRistorante;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.PreferitiClienteUI;
import menu.RecensioneUI;
import repository.PreferitiClienteService;
import repository.RecensioneService;
import repository.RistoranteService;
import repository.UtenteService;
/**
 *
 * @author armuh
 */
public class TheKnife {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Scanner scanner = new Scanner(System.in);
//        PreferitiClienteService p = new PreferitiClienteService();
//        PreferitiClienteUI pref = new PreferitiClienteUI(scanner, p);
//        UtenteService us = new UtenteService();
//        RistoranteService rs = new RistoranteService();
//        p.remove(us.get("eleonoracaredda"), rs.get("Amador"));
        GestioneMenu menu = new GestioneMenu();
    }

}
