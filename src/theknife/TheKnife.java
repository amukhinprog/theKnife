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
import repository.PreferitiClienteService;
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
//        System.out.println(Utente.inserisciRuolo());
        
//        Ristorante.inserisciRistoranteEsistente((Gestore) Gestore.getUtenti().get("artur1"));
        RistoranteService rs= new RistoranteService();
        PreferitiClienteService pcs = new PreferitiClienteService();
        pcs.add("eleonoracaredda", rs.get("Alinea"));
        pcs.add("eleonoracaredda", rs.get("Amador"));
//        pcs.add("eleonoracaredda", rs.get("Smyth"));
        GestioneMenu menu = new GestioneMenu();
    }

}
